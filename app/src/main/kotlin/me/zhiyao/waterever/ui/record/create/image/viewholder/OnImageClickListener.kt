package me.zhiyao.waterever.ui.record.create.image.viewholder

import android.view.View

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
interface OnImageClickListener {

    fun onImageClicked(view: View, imagePath: String)

    fun onRemoveClicked(imagePath: String)
}