package me.zhiyao.waterever.ui.main.home.entity

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
)
