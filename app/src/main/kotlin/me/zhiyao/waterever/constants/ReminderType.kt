package me.zhiyao.waterever.constants

/**
 *
 * @author WangZhiYao
 * @date 2020/8/11
 */
enum class ReminderType constructor(private val id: Int) {

    WATERING(1),
    FERTILIZE(2),
    CHANGE_SOIL(3);

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