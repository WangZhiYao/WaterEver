package me.zhiyao.waterever.ui.record.create.image.viewholder

import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.config.GlideApp
import me.zhiyao.waterever.databinding.ItemNewGrowthRecordImageBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
class NewGrowthRecordImageViewHolder(
    private val binding: ItemNewGrowthRecordImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imagePath: String, onImageClickListener: OnImageClickListener) {
        binding.ivGrowthRecordImage.run {
            GlideApp.with(this)
                .load(imagePath)
                .into(this)
            setOnClickListener {
                onImageClickListener.onImageClicked(imagePath)
            }
        }

        binding.ivGrowthRecordImageRemove.setOnClickListener {
            onImageClickListener.onRemoveClicked(imagePath)
        }
    }
}