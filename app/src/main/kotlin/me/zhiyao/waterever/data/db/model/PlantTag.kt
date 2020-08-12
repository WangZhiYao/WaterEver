package me.zhiyao.waterever.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(tableName = "plant_tags")
data class PlantTag(
    val name: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_tag_id")
    var id: Long = 0
}