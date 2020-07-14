package me.zhiyao.waterever.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.zhiyao.waterever.db.model.GrowthRecord
import me.zhiyao.waterever.db.model.Image
import me.zhiyao.waterever.db.model.relations.GrowthRecordImageRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
data class GrowthRecordDTO(
    @Embedded
    val growthRecord: GrowthRecord,
    @Relation(
        parentColumn = "growth_record_id",
        entityColumn = "image_id",
        associateBy = Junction(GrowthRecordImageRelation::class)
    )
    val imageList: List<Image>
)