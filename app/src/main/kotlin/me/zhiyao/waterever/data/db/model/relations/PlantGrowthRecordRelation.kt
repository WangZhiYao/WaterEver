package me.zhiyao.waterever.data.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import me.zhiyao.waterever.data.db.model.GrowthRecord
import me.zhiyao.waterever.data.db.model.Plant

/**
 *
 * @author WangZhiYao
 * @date 2020/9/14
 */
@Entity(
    tableName = "plant_growth_record_relations",
    primaryKeys = [
        "plant_id",
        "growth_record_id"
    ],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GrowthRecord::class,
            parentColumns = ["growth_record_id"],
            childColumns = ["growth_record_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"]),
        Index(value = ["growth_record_id"]),
        Index(value = ["plant_id", "growth_record_id"], unique = true)
    ]
)
data class PlantGrowthRecordRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "growth_record_id")
    val growthRecordId: Long
)