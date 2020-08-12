package me.zhiyao.waterever.data.db.model

import androidx.room.*
import me.zhiyao.waterever.constants.ReminderType

/**
 *
 * @author WangZhiYao
 * @date 2020/8/11
 */
@Entity(
    tableName = "plant_reminders",
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"])
    ]
)
data class PlantReminder(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "reminder_type")
    val reminderType: ReminderType
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_reminder_id")
    var id: Long = 0
}