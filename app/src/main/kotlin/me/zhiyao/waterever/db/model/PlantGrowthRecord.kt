package me.zhiyao.waterever.db.model

import androidx.room.*
import me.zhiyao.waterever.constants.PlantGrowthRecordType

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(
    tableName = "plant_growth_record",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"])
    ]
)
data class PlantGrowthRecord(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    val type: PlantGrowthRecordType,
    val remark: String?,
    val amount: Int?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "growth_record_id")
    var growthRecordId: Long = 0
}