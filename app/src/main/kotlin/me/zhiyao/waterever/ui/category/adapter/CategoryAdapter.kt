package me.zhiyao.waterever.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.ItemCategoryBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.exts.tipsViewHolder
import me.zhiyao.waterever.ui.category.viewholder.CategoryViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CATEGORY = 1
        private const val VIEW_TYPE_EMPTY = 2
    }

    private var categories: List<Category>? = null

    private var selectionMode = false
    private var selectedList: ArrayList<Category> = ArrayList()

    private var onCategoryClickListener: OnCategoryClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    fun setCategories(categories: List<Category>) {
        this.categories = categories
        selectedList.clear()
        notifyDataSetChanged()
    }

    fun isCategoryExist(name: String): Boolean {
        categories?.run {
            for (category in this) {
                if (category.name.toLowerCase(Locale.getDefault())
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

    fun getSelectedItem(): List<Category> = selectedList

    fun setOnCategoryClickListener(onCategoryClickListener: OnCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CATEGORY -> CategoryViewHolder(
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_EMPTY -> parent.tipsViewHolder()
            else -> parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_CATEGORY -> {
                categories?.get(position)?.let { category ->
                    (holder as CategoryViewHolder).bind(
                        category,
                        selectionMode,
                        selectedList.contains(category)
                    )
                    holder.itemView.setOnClickListener {
                        if (selectionMode) {
                            if (selectedList.contains(category)) {
                                selectedList.remove(category)
                            } else {
                                selectedList.add(category)
                            }
                            notifyItemChanged(position)
                        } else {
                            onCategoryClickListener?.onCategoryClicked(category)
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
        if (categories.isNullOrEmpty()) 1 else categories!!.size

    override fun getItemViewType(position: Int): Int =
        if (categories.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_CATEGORY

    interface OnCategoryClickListener {

        fun onCategoryClicked(category: Category)
    }

    interface OnItemLongClickListener {

        fun onLongClicked()
    }
}
