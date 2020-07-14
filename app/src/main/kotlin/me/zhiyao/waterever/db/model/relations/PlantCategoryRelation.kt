package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(
    tableName = "plant_category_relation",
    primaryKeys = [
        "plant_id",
        "plant_category_id"
    ]
)
data class PlantCategoryRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Int,
    @ColumnInfo(name = "plant_category_id")
    val plantCategoryId: Int
)