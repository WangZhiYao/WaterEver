package me.zhiyao.waterever.data.db.model

import androidx.room.*

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(
    tableName = "images",
    foreignKeys = [
        ForeignKey(
            entity = GrowthRecord::class,
            parentColumns = ["growth_record_id"],
            childColumns = ["growth_record_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["growth_record_id"])
    ]
)
data class Image(
    @ColumnInfo(name = "growth_record_id")
    val growthRecordId: Long?,
    val path: String,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    var id: Long = 0
}