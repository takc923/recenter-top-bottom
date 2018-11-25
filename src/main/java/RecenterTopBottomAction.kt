import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType
import com.intellij.openapi.editor.actionSystem.EditorAction
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.util.Key

class RecenterTopBottomAction : EditorAction(Handler()) {
    internal class Handler : EditorActionHandler() {
        override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
            val tmpScrollKind = editor.getUserData(SCROLL_KIND_KEY)
            if (tmpScrollKind == null) editor.caretModel.addCaretListener(MyCaretListener())

            val scrollKind = tmpScrollKind ?: ScrollKind.RECENTER
            val scroll = editor.scrollingModel
            val caretOffset = editor.offsetToXY(editor.caretModel.offset).y
            when (scrollKind) {
                ScrollKind.RECENTER -> scroll.scrollToCaret(ScrollType.CENTER)
                ScrollKind.TOP -> scroll.scrollVertically(caretOffset - editor.lineHeight)
                ScrollKind.BOTTOM -> scroll.scrollVertically(caretOffset - scroll.visibleArea.height + 2 * editor.lineHeight)
            }
            editor.putUserData(SCROLL_KIND_KEY, scrollKind.next())
        }

        private class MyCaretListener : CaretListener {
            override fun caretPositionChanged(e: CaretEvent) {
                e.editor.putUserData(SCROLL_KIND_KEY, ScrollKind.RECENTER)
            }
        }

        companion object {
            private val SCROLL_KIND_KEY = Key.create<ScrollKind>("RecenterTopBottomHandler.SCROLL_KIND_KEY")
            enum class ScrollKind {
                RECENTER, TOP, BOTTOM;
                fun next(): ScrollKind = values()[(this.ordinal + 1) % values().size]
            }
        }
    }
}