package me.zhiyao.waterever.ui.main.home.adapter

import androidx.recyclerview.widget.DiffUtil
import me.zhiyao.waterever.ui.main.home.entity.HomeItem

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
object HomeItemComparator : DiffUtil.ItemCallback<HomeItem>() {
    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.growthRecord.id == newItem.growthRecord.id
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.growthRecord.id == newItem.growthRecord.id
    }
}