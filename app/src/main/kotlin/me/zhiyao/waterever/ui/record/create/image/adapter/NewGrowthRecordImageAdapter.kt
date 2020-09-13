package me.zhiyao.waterever.ui.record.create.image.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.databinding.ItemNewGrowthRecordImageBinding
import me.zhiyao.waterever.ui.record.create.image.viewholder.NewGrowthRecordImageViewHolder
import me.zhiyao.waterever.ui.record.create.image.viewholder.OnImageClickListener

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
class NewGrowthRecordImageAdapter : RecyclerView.Adapter<NewGrowthRecordImageViewHolder>() {

    private var images: MutableList<String>? = null

    private var onImageClickListener: OnImageClickListener? = null

    fun setImages(images: List<String>) {
        this.images = images.toMutableList()
        notifyDataSetChanged()
    }

    fun onImageRemoved(imagePath: String) {
        images?.indexOf(imagePath)?.let { position ->
            if (position != -1) {
                images?.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, images!!.size - position)
            }
        }
    }

    fun setOnImageClickListener(onImageClickListener: OnImageClickListener) {
        this.onImageClickListener = onImageClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewGrowthRecordImageViewHolder(
            ItemNewGrowthRecordImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewGrowthRecordImageViewHolder, position: Int) {
        images?.let {
            holder.bind(it[position], object : OnImageClickListener {
                override fun onImageClicked(imagePath: String) {
                    onImageClickListener?.onImageClicked(imagePath)
                }

                override fun onRemoveClicked(imagePath: String) {
                    onImageClickListener?.onRemoveClicked(imagePath)
                }
            })
        }
    }

    override fun getItemCount() = images?.size ?: 0
}