package me.zhiyao.waterever.db.converters

import androidx.room.TypeConverter
import me.zhiyao.waterever.constants.ReminderType

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
class ReminderTypeConverter {

    @TypeConverter
    fun formReminderType(reminderType: ReminderType): Int {
        return reminderType.getId()
    }

    @TypeConverter
    fun toReminderType(id: Int): ReminderType? {
        return ReminderType.getReminderType(id)
    }
}