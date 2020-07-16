package me.zhiyao.waterever.constants

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
enum class ReminderPeriodType(private val id: Int) {

    FIXED(1),
    CUSTOMIZED(2);

    fun getId(): Int {
        return id
    }

    companion object {
        fun getReminderPeriodType(id: Int): ReminderPeriodType? {
            for (value in values()) {
                if (value.getId() == id) {
                    return value
                }
            }

            return null
        }
    }
}