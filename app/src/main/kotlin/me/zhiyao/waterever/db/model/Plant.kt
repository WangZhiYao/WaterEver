package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.zhiyao.waterever.constants.PlantState

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@Entity(tableName = "plant")
data class Plant(
    var name: String,
    var remarks: String?,
    var state: PlantState,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    var plantId: Long = 0
}