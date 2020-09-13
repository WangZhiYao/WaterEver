package me.zhiyao.waterever.ui.image.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.databinding.ItemImageViewerBinding
import me.zhiyao.waterever.ui.image.viewholder.ImageViewerViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
class ImageViewerAdapter : RecyclerView.Adapter<ImageViewerViewHolder>() {

    private var images: List<String>? = null

    fun setImages(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewerViewHolder(
            ItemImageViewerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImageViewerViewHolder, position: Int) {
        images?.get(position)?.let { imagePath ->
            holder.bind(imagePath)
        }
    }

    override fun getItemCount() =
        images?.size ?: 0
}