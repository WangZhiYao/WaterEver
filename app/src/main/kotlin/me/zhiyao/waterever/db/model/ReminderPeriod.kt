package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
@Entity(
    tableName = "reminder_period"
)
data class ReminderPeriod(
    @ColumnInfo(name = "period_type")
    var periodType: Int,
    @ColumnInfo(name = "start_time")
    var startTime: Int?,
    @ColumnInfo(name = "end_time")
    var endTime: Int?,
    var period: Int
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reminder_period_id")
    var reminderPeriodId: Long = 0
}