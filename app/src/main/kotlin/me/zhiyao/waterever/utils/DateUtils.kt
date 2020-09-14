package me.zhiyao.waterever.utils

import android.content.Context
import me.zhiyao.waterever.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author WangZhiYao
 * @date 2020/9/14
 */
object DateUtils {

    const val PATTERN_YYYY_MM_DD = "yyyy-MM-dd"

    fun toDateString(timestamp: Long, pattern: String): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    fun toTimeInterval(context: Context, timestamp: Long): String {
        val days = TimeUnit.MILLISECONDS.toDays(timestamp)
        if (days > 0) {
            if (days > 3) {
                return toDateString(timestamp, PATTERN_YYYY_MM_DD)
            }
            return context.getString(R.string.time_interval_day, days)
        }
        val hours = TimeUnit.MILLISECONDS.toHours(timestamp)
        if (hours > 0) {
            return context.getString(R.string.time_interval_hour, hours)
        }
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timestamp)
        if (minutes > 0) {
            return context.getString(R.string.time_interval_minute, minutes)
        }

        val seconds = TimeUnit.MILLISECONDS.toSeconds(timestamp).coerceAtLeast(1)
        return context.getString(R.string.time_interval_second, seconds)
    }
}