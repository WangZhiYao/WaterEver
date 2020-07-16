package me.zhiyao.waterever.db.converters

import androidx.room.TypeConverter
import me.zhiyao.waterever.constants.ReminderPeriodType

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
class ReminderPeriodTypeConverter {

    @TypeConverter
    fun formPeriodType(reminderPeriodType: ReminderPeriodType): Int {
        return reminderPeriodType.getId()
    }

    @TypeConverter
    fun toPeriodType(id: Int): ReminderPeriodType? {
        return ReminderPeriodType.getReminderPeriodType(id)
    }
}