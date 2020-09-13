package me.zhiyao.waterever.ui.record.create.image.viewholder

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
interface OnImageClickListener {

    fun onImageClicked(imagePath: String)

    fun onRemoveClicked(imagePath: String)
}