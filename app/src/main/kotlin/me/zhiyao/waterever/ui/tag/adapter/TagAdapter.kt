package me.zhiyao.waterever.ui.tag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.databinding.ItemTagBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.exts.tipsViewHolder
import me.zhiyao.waterever.ui.tag.viewholder.TagViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class TagAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TAG = 1
        private const val VIEW_TYPE_EMPTY = 2
    }

    private var tags: List<Tag>? = null

    private var selectionMode = false
    private var selectedList: ArrayList<Tag> = ArrayList()

    private var onTagClickListener: OnTagClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    fun setTags(tags: List<Tag>) {
        this.tags = tags
        selectedList.clear()
        notifyDataSetChanged()
    }

    fun isTagExist(name: String): Boolean {
        tags?.run {
            for (tag in this) {
                if (tag.name.toLowerCase(Locale.getDefault())
                    == name.toLowerCase(Locale.ROOT)
                ) {
                    return true
                }
            }
        }

        return false
    }

    fun setSelectionMode(selectionMode: Boolean) {
        this.selectionMode = selectionMode
        selectedList.clear()
        notifyDataSetChanged()
    }

    fun isSelectionMode(): Boolean = selectionMode

    fun getSelectedItem(): List<Tag> = selectedList

    fun setOnTagClickListener(onTagClickListener: OnTagClickListener) {
        this.onTagClickListener = onTagClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TAG -> TagViewHolder(
                ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_EMPTY -> parent.tipsViewHolder()
            else -> parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_TAG -> {
                tags?.get(position)?.let { tag ->
                    (holder as TagViewHolder).bind(
                        tag,
                        selectionMode,
                        selectedList.contains(tag)
                    )
                    holder.itemView.setOnClickListener {
                        if (selectionMode) {
                            if (selectedList.contains(tag)) {
                                selectedList.remove(tag)
                            } else {
                                selectedList.add(tag)
                            }
                            notifyItemChanged(position)
                        } else {
                            onTagClickListener?.onTagClicked(tag)
                        }
                    }
                    holder.itemView.setOnLongClickListener {
                        onItemLongClickListener?.onLongClicked()
                        true
                    }
                }
            }
            VIEW_TYPE_EMPTY -> (holder as TipsViewHolder).bind(
                holder.itemView.context.getString(R.string.categories_empty_notice)
            )
        }
    }

    override fun getItemCount(): Int =
        if (tags.isNullOrEmpty()) 1 else tags!!.size

    override fun getItemViewType(position: Int): Int =
        if (tags.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_TAG

    interface OnTagClickListener {

        fun onTagClicked(tag: Tag)
    }

    interface OnItemLongClickListener {

        fun onLongClicked()
    }
}
