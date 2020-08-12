package me.zhiyao.waterever.data.db.model

import androidx.room.*
import me.zhiyao.waterever.constants.PlantState

/**
 *
 * @author WangZhiYao
 * @date 2020/7/29
 */
@Entity(
    tableName = "plants",
    foreignKeys = [
        ForeignKey(
            entity = PlantCategory::class,
            parentColumns = ["plant_category_id"],
            childColumns = ["plant_category_id"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_category_id"])
    ]
)
data class Plant(
    var name: String,
    var state: PlantState,
    @ColumnInfo(name = "feature_image")
    var featureImage: String?,
    @ColumnInfo(name = "plant_category_id")
    var plantCategoryId: Long?,
    var description: String?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    var id: Long = 0
}