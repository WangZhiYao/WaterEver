package me.zhiyao.waterever.db.model

import androidx.room.*

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
@Entity(
    tableName = "plant_image",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlantGrowthRecord::class,
            parentColumns = ["growth_record_id"],
            childColumns = ["growth_record_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"]),
        Index(value = ["growth_record_id"])
    ]
)
data class PlantImage(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "growth_record_id")
    val growthRecordId: Long?,
    val path: String,
    var featured: Boolean?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_image_id")
    var plantImageId: Long = 0
}