package me.zhiyao.waterever.constants

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
enum class PlantState(private val id: Int) {

    ALIVE(1),
    DIED(2);

    fun getId(): Int {
        return id
    }

    companion object {
        fun getPlantState(id: Int): PlantState? {
            for (value in values()) {
                if (value.getId() == id) {
                    return value
                }
            }

            return null
        }
    }
}