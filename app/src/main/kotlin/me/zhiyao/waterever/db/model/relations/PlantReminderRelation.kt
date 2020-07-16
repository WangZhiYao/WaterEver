package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import me.zhiyao.waterever.db.model.Plant
import me.zhiyao.waterever.db.model.Reminder

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
@Entity(
    tableName = "plant_reminder_relation",
    primaryKeys = ["plant_id", "reminder_id"],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Reminder::class,
            parentColumns = ["reminder_id"],
            childColumns = ["reminder_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"]),
        Index(value = ["reminder_id"]),
        Index(value = ["plant_id", "reminder_id"], unique = true)
    ]
)
class PlantReminderRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "reminder_id")
    val reminderId: Long
)