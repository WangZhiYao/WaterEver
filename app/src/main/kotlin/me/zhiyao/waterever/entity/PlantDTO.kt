package me.zhiyao.waterever.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.zhiyao.waterever.db.model.GrowthRecord
import me.zhiyao.waterever.db.model.Plant
import me.zhiyao.waterever.db.model.relations.PlantGrowthRecordRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
data class PlantDTO(
    @Embedded
    val plant: Plant,
    @Relation(
        parentColumn = "plant_id",
        entityColumn = "growth_record_id",
        entity = GrowthRecord::class,
        associateBy = Junction(PlantGrowthRecordRelation::class)
    )
    val growthRecordList: List<GrowthRecordDTO>
)