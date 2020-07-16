package me.zhiyao.waterever.db.converters

import androidx.room.TypeConverter
import me.zhiyao.waterever.constants.PlantGrowthRecordType

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
class PlantGrowthRecordTypeConverter {

    @TypeConverter
    fun formGrowthRecordType(plantGrowthRecordType: PlantGrowthRecordType): Int {
        return plantGrowthRecordType.getId()
    }

    @TypeConverter
    fun toGrowthRecordType(id: Int): PlantGrowthRecordType? {
        return PlantGrowthRecordType.getPlantGrowthRecordType(id)
    }
}