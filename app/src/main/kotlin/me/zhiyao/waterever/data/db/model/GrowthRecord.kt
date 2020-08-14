package me.zhiyao.waterever.data.db.model

import androidx.room.*
import me.zhiyao.waterever.constants.GrowthRecordType

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(
    tableName = "growth_records",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"])
    ]
)
data class GrowthRecord(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "record_type")
    val recordType: GrowthRecordType,
    var description: String?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "growth_record_id")
    var id: Long = 0
}