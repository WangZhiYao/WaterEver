package me.zhiyao.waterever.data.db.model

import androidx.room.*

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(
    tableName = "plant_images",
    foreignKeys = [
        ForeignKey(
            entity = PlantGrowthRecord::class,
            parentColumns = ["plant_growth_record_id"],
            childColumns = ["plant_growth_record_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_growth_record_id"])
    ]
)
data class PlantImage(
    @ColumnInfo(name = "plant_growth_record_id")
    val plantGrowthRecordId: Long?,
    val path: String,
    var feature: Boolean,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_image_id")
    var id: Long = 0
}