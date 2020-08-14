package me.zhiyao.waterever.ui.main.home.entity

import androidx.recyclerview.widget.DiffUtil
import me.zhiyao.waterever.data.db.model.*

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
data class HomeItem(
    val plant: Plant,
    val category: Category?,
    val tagList: List<Tag>?,
    val growthRecord: GrowthRecord,
    val imageList: List<Image>?
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<HomeItem>() {
            override fun areItemsTheSame(
                oldItem: HomeItem,
                newItem: HomeItem
            ): Boolean {
                return oldItem.plant.id == newItem.plant.id
            }

            override fun areContentsTheSame(
                oldItem: HomeItem,
                newItem: HomeItem
            ): Boolean {
                return oldItem.plant.id == newItem.plant.id
            }
        }
    }
}