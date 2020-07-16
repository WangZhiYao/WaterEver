package me.zhiyao.waterever.db.converters

import androidx.room.TypeConverter
import me.zhiyao.waterever.constants.PlantState

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
class PlantStateConverter {

    @TypeConverter
    fun formPlantState(plantState: PlantState): Int {
        return plantState.getId()
    }

    @TypeConverter
    fun toPlantState(id: Int): PlantState? {
        return PlantState.getPlantState(id)
    }
}