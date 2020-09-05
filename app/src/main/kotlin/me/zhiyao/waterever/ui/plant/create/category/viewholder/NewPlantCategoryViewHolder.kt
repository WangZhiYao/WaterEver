package me.zhiyao.waterever.ui.plant.create.category.viewholder

import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.ItemNewPlantCategoryBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/9/5
 */
class NewPlantCategoryViewHolder(
    private val binding: ItemNewPlantCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, selected: Boolean) {
        binding.tvCategoryName.text = category.name
        binding.cbCategory.isChecked = selected
    }
}