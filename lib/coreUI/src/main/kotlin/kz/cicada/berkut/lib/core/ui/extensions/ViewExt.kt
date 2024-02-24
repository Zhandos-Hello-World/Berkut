package kz.cicada.berkut.lib.core.ui.extensions

import androidx.appcompat.widget.Toolbar

fun Toolbar.setOnMenuItemSafeClickListener(listener: Toolbar.OnMenuItemClickListener?) {
    var lastClickedTime = 0L
    setOnMenuItemClickListener {
        val clickedTime = System.currentTimeMillis()
        if (clickedTime > lastClickedTime + 600) {
            lastClickedTime = clickedTime
            listener?.onMenuItemClick(it)
        }
        true
    }
}