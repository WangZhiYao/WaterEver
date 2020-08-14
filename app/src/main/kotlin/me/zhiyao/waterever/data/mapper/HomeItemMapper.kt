package me.zhiyao.waterever.data.mapper

import me.zhiyao.waterever.data.db.entities.PlantGrowthRecord
import me.zhiyao.waterever.ui.main.home.entity.HomeItem

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
class HomeItemMapper : BaseMapper<PlantGrowthRecord, HomeItem> {

    override fun map(input: PlantGrowthRecord): HomeItem =
        HomeItem(
            input.plantWithCategoryTags.plant,
            input.plantWithCategoryTags.category,
            input.plantWithCategoryTags.tagList,
            input.growthRecordWithImage.growthRecord,
            input.growthRecordWithImage.imageList
        )
}