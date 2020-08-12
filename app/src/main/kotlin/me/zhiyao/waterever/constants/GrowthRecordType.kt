package me.zhiyao.waterever.constants

import android.content.Context
import me.zhiyao.waterever.R

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
enum class GrowthRecordType(private val id: Int) {

    WATERING(1),
    CHANGE_SOIL(2),
    FERTILIZE(3),
    GROWTH(4);

    fun getId(): Int {
        return id
    }

    companion object {
        fun getPlantGrowthRecordType(id: Int): GrowthRecordType? {
            for (value in values()) {
                if (value.getId() == id) {
                    return value
                }
            }

            return null
        }

        fun GrowthRecordType.toRecordType(context: Context): String? {
            return when (this) {
                WATERING -> context.getString(R.string.plant_growth_record_type_watering)
                CHANGE_SOIL -> context.getString(R.string.plant_growth_record_type_change_soil)
                FERTILIZE -> context.getString(R.string.plant_growth_record_type_fertilize)
                GROWTH -> context.getString(R.string.plant_growth_record_type_growth)
            }
        }
    }
}