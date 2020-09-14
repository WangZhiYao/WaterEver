package me.zhiyao.waterever.data.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.relations.PlantGrowthRecordRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
data class PlantGrowthRecord(
    @Relation(
        parentColumn = "growth_record_id",
        entityColumn = "plant_id",
        entity = Plant::class,
        associateBy = Junction(PlantGrowthRecordRelation::class)
    )
    val plantWithCategoryTags: PlantWithCategoryTags,
    @Embedded
    val growthRecordWithImage: GrowthRecordWithImage
)