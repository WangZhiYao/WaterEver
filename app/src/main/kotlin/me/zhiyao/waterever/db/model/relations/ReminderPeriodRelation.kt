package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
@Entity(
    tableName = "reminder_period_relation",
    primaryKeys = [
        "reminder_id",
        "reminder_period_id"
    ]
)
data class ReminderPeriodRelation(
    @ColumnInfo(name = "reminder_id")
    val reminderId: Long,
    @ColumnInfo(name = "reminder_period_id")
    val reminderPeriodId: Long
)