package me.zhiyao.waterever.constants

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
enum class PlantGrowthRecordType(private val id: Int) {

    WATERING(1),
    CHANGE_SOIL(2),
    FERTILIZE(3),
    GROWTH(4);

    fun getId(): Int {
        return id
    }

    companion object {
        fun getPlantGrowthRecordType(id: Int): PlantGrowthRecordType? {
            for (value in values()) {
                if (value.getId() == id) {
                    return value
                }
            }

            return null
        }
    }
}