package me.zhiyao.waterever.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import me.zhiyao.waterever.data.db.model.GrowthRecord
import me.zhiyao.waterever.data.db.model.Image

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
data class GrowthRecordWithImage(
    @Embedded
    val growthRecord: GrowthRecord,
    @Relation(
        parentColumn = "growth_record_id",
        entityColumn = "growth_record_id"
    )
    val imageList: List<Image>
)