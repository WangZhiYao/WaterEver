package me.zhiyao.waterever.ui.plant.create.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.ItemNewPlantCategoryBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.exts.tipsViewHolder
import me.zhiyao.waterever.ui.plant.create.category.viewholder.NewPlantCategoryViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/9/5
 */
class NewPlantCategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CATEGORY = 1
        private const val VIEW_TYPE_EMPTY = 2
    }

    private var categories: List<Category>? = null
    private var onCategoryClickListener: OnCategoryClickListener? = null

    private var selectedCategory: Category? = null

    fun setCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    fun setOnCategoryClickListener(onCategoryClickListener: OnCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener
    }

    fun setSelectedCategory(category: Category) {
        selectedCategory = category
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CATEGORY -> NewPlantCategoryViewHolder(
                ItemNewPlantCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_EMPTY -> parent.tipsViewHolder()
            else -> parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_CATEGORY -> {
                categories?.get(position)?.let { category ->
                    (holder as NewPlantCategoryViewHolder).bind(
                        category,
                        selectedCategory == category
                    )

                    holder.itemView.setOnClickListener {
                        if (selectedCategory == category) {
                            selectedCategory = null
                        } else {
                            var lastPosition: Int? = null

                            if (selectedCategory != null) {
                                categories?.let { categories ->
                                    for ((index, value) in categories.withIndex()) {
                                        if (value == selectedCategory) {
                                            lastPosition = index
                                            break
                                        }
                                    }
                                }
                            }

                            selectedCategory = category

                            lastPosition?.let {
                                notifyItemChanged(it)
                            }
                        }

                        onCategoryClickListener?.onCategoryClicked(selectedCategory)
                        notifyItemChanged(position)
                    }
                }
            }
            VIEW_TYPE_EMPTY -> (holder as TipsViewHolder).bind(
                holder.itemView.context.getString(R.string.new_plant_category_empty_notice)
            )
        }
    }

    override fun getItemCount(): Int =
        if (categories.isNullOrEmpty()) 1 else categories!!.size

    override fun getItemViewType(position: Int): Int =
        if (categories.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_CATEGORY

    interface OnCategoryClickListener {

        fun onCategoryClicked(selectedCategory: Category?)
    }
}