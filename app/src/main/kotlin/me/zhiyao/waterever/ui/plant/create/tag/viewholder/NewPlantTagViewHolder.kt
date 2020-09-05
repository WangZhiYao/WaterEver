package me.zhiyao.waterever.ui.plant.create.tag.viewholder

import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.databinding.ItemNewPlantTagBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/9/5
 */
class NewPlantTagViewHolder(private val binding: ItemNewPlantTagBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: Tag, selected: Boolean) {
        binding.cbTag.isChecked = selected
        binding.tvTagName.text = tag.name
    }
}