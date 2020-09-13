package me.zhiyao.waterever.ui.image.viewholder

import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.config.GlideApp
import me.zhiyao.waterever.databinding.ItemImageViewerBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
class ImageViewerViewHolder(
    private val binding: ItemImageViewerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imagePath: String) {
        binding.pvImageViewer.isZoomable = true
        GlideApp.with(binding.pvImageViewer)
            .load(imagePath)
            .into(binding.pvImageViewer)
    }
}