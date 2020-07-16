package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import me.zhiyao.waterever.db.model.Plant
import me.zhiyao.waterever.db.model.PlantCategory

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(
    tableName = "plant_category_relation",
    primaryKeys = ["plant_id", "plant_category_id"],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlantCategory::class,
            parentColumns = ["plant_category_id"],
            childColumns = ["plant_category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"]),
        Index(value = ["plant_category_id"]),
        Index(value = ["plant_id", "plant_category_id"], unique = true)
    ]
)
data class PlantCategoryRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "plant_category_id")
    val plantCategoryId: Long
)