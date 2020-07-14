package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(
    tableName = "plant_category"
)
data class PlantCategory(
    val name: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_category_id")
    var plantCategoryId: Long = 0
}