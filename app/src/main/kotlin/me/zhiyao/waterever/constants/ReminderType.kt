package me.zhiyao.waterever.constants

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
enum class ReminderType(private val id: Int) {

    WATERING(1),
    CHANGE_SOIL(2),
    FERTILIZE(3);

    fun getId(): Int {
        return id
    }

    companion object {
        fun getReminderType(id: Int): ReminderType? {
            for (value in values()) {
                if (value.getId() == id) {
                    return value
                }
            }

            return null
        }
    }
}