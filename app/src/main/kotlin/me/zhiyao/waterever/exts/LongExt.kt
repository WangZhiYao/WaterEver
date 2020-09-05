package me.zhiyao.waterever.exts

import android.content.Context
import me.zhiyao.waterever.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
const val YYYY_MM_DD = "yyyy.MM.dd"

fun Long.toTimeInterval(context: Context): String {
    val days = TimeUnit.MILLISECONDS.toDays(this)
    if (days > 0) {
        if (days > 3) {
            return toDateString(YYYY_MM_DD)
        }
        return context.getString(R.string.time_interval_day, days)
    }
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    if (hours > 0) {
        return context.getString(R.string.time_interval_hour, hours)
    }
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    if (minutes > 0) {
        return context.getString(R.string.time_interval_minute, minutes)
    }

    val seconds = TimeUnit.MILLISECONDS.toSeconds(this).coerceAtLeast(1)
    return context.getString(R.string.time_interval_second, seconds)
}

fun Long.toDateString(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date(this))
}