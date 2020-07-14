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
    tableName = "reminder"
)
data class Reminder(
    @ColumnInfo(name = "plant_id")
    val plantId: Int,
    val type: Int,
    @ColumnInfo(name = "last_time")
    var lastTime: Long?,
    var remarks: String?
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reminder_id")
    var reminderId: Long = 0
}