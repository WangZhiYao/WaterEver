package me.zhiyao.waterever.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import me.zhiyao.waterever.data.db.model.GrowthRecord

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
data class PlantGrowthRecord(
    @Embedded
    val plantWithCategoryTags: PlantWithCategoryTags,
    @Relation(
        parentColumn = "plant_id",
        entityColumn = "plant_id",
        entity = GrowthRecord::class
    )
    val growthRecordWithImage: GrowthRecordWithImage
)