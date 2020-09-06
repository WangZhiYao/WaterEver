package me.zhiyao.waterever.ui.tag.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.databinding.ItemTagBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class TagViewHolder(private val binding: ItemTagBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: Tag, isSelectionMode: Boolean, isChecked: Boolean) {
        binding.tvTagName.text = tag.name
        binding.cbTag.visibility = if (isSelectionMode) View.VISIBLE else View.GONE
        binding.cbTag.isChecked = isSelectionMode && isChecked
    }
}