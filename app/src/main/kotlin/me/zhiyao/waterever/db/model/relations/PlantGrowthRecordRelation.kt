package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
@Entity(
    tableName = "plant_growth_record_relation",
    primaryKeys = [
        "plant_id",
        "growth_record_id"
    ]
)
data class PlantGrowthRecordRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "growth_record_id", index = true)
    val growthRecordId: Long
)