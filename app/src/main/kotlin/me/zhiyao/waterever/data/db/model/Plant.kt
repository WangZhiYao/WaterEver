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
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["category_id"])
    ]
)
data class Plant(
    @ColumnInfo(name = "plant_name")
    var name: String,
    @ColumnInfo(name = "plant_state")
    var state: PlantState,
    @ColumnInfo(name = "feature_image")
    var featureImage: String?,
    @ColumnInfo(name = "category_id")
    var categoryId: Long?,
    var description: String?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    var id: Long = 0
}