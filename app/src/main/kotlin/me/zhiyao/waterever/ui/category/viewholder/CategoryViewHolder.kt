package me.zhiyao.waterever.ui.category.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.ItemCategoryBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, isSelectionMode: Boolean, isChecked: Boolean) {
        binding.tvCategoryName.text = category.name
        binding.cbCategory.visibility = if (isSelectionMode) View.VISIBLE else View.GONE
        binding.cbCategory.isChecked = isSelectionMode && isChecked
    }
}