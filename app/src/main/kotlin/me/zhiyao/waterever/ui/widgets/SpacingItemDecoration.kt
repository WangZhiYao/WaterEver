package me.zhiyao.waterever.ui.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author WangZhiYao
 * @date 2020/9/14
 */
class SpacingItemDecoration : RecyclerView.ItemDecoration {

    private val spacing: Int
    private val includeEdge: Boolean

    constructor(
        spacing: Int
    ) : this(spacing, false)

    constructor(
        spacing: Int,
        includeEdge: Boolean
    ) {
        this.spacing = spacing
        this.includeEdge = includeEdge
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = spacing

        if (includeEdge) {
            outRect.left = spacing
            outRect.right = spacing
        }

        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.top = spacing
        }
    }
}