package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import me.zhiyao.waterever.db.model.Reminder
import me.zhiyao.waterever.db.model.ReminderPeriod

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
@Entity(
    tableName = "reminder_period_relation",
    primaryKeys = ["reminder_id", "reminder_period_id"],
    foreignKeys = [
        ForeignKey(
            entity = Reminder::class,
            parentColumns = ["reminder_id"],
            childColumns = ["reminder_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ReminderPeriod::class,
            parentColumns = ["reminder_period_id"],
            childColumns = ["reminder_period_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["reminder_id"]),
        Index(value = ["reminder_period_id"]),
        Index(value = ["reminder_id", "reminder_period_id"], unique = true)
    ]
)
data class ReminderPeriodRelation(
    @ColumnInfo(name = "reminder_id")
    val reminderId: Long,
    @ColumnInfo(name = "reminder_period_id")
    val reminderPeriodId: Long
)