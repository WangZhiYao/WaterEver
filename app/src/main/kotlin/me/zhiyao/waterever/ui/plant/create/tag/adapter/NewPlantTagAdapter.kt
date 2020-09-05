package me.zhiyao.waterever.ui.plant.create.tag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.databinding.ItemNewPlantTagBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.exts.tipsViewHolder
import me.zhiyao.waterever.ui.plant.create.tag.viewholder.NewPlantTagViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/9/5
 */
class NewPlantTagAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TAG = 1
        private const val VIEW_TYPE_EMPTY = 2
    }

    private var tags: List<Tag>? = null
    private var onTagClickListener: OnTagClickListener? = null

    var selectedList: MutableList<Long> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setTags(tags: List<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }

    fun setOnTagClickListener(onTagClickListener: OnTagClickListener) {
        this.onTagClickListener = onTagClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TAG -> NewPlantTagViewHolder(
                ItemNewPlantTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_EMPTY -> parent.tipsViewHolder()
            else -> parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_TAG -> {
                tags?.get(position)?.let { tag ->
                    (holder as NewPlantTagViewHolder).bind(tag, selectedList.contains(tag.id))
                    holder.itemView.setOnClickListener {
                        if (selectedList.contains(tag.id)) {
                            selectedList.remove(tag.id)
                        } else {
                            selectedList.add(tag.id)
                        }
                        onTagClickListener?.onTagClicked(selectedList)
                        notifyItemChanged(position)
                    }
                }
            }
            VIEW_TYPE_EMPTY -> (holder as TipsViewHolder).bind(
                holder.itemView.context.getString(R.string.categories_empty_notice)
            )
        }
    }

    override fun getItemCount(): Int = tags?.size ?: 0

    override fun getItemViewType(position: Int): Int =
        if (tags.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_TAG

    interface OnTagClickListener {

        fun onTagClicked(tagIds: List<Long>)
    }
}
