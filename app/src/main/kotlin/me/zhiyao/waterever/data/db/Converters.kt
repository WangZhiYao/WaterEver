package me.zhiyao.waterever.data.db

import androidx.room.TypeConverter
import me.zhiyao.waterever.constants.GrowthRecordType
import me.zhiyao.waterever.constants.PlantState
import me.zhiyao.waterever.constants.ReminderType

/**
 *
 * @author WangZhiYao
 * @date 2020/7/29
 */
class Converters {

    @TypeConverter
    fun fromPlantState(plantState: PlantState) =
        plantState.getId()

    @TypeConverter
    fun toPlantState(plantStateId: Int) =
        PlantState.getPlantState(plantStateId)

    @TypeConverter
    fun fromGrowthRecordType(growthRecordType: GrowthRecordType) =
        growthRecordType.getId()

    @TypeConverter
    fun toGrowthRecordType(growthRecordTypeId: Int) =
        GrowthRecordType.getPlantGrowthRecordType(growthRecordTypeId)

    @TypeConverter
    fun formReminderType(reminderType: ReminderType) =
        reminderType.getId()

    @TypeConverter
    fun toReminderType(id: Int) =
        ReminderType.getReminderType(id)
}
