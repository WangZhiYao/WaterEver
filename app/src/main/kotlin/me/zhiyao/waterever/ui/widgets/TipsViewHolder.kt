package me.zhiyao.waterever.ui.widgets

import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.databinding.ItemTipsBinding

/**
 *
 * @author WangZhiYao
 * @date 2020/8/27
 */
class TipsViewHolder(private val binding: ItemTipsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tips: String) {
        binding.tvTips.text = tips
    }
}