package me.zhiyao.waterever.utils

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.ItemEmptyBinding
import me.zhiyao.waterever.ui.widgets.EmptyViewHolder
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

/**
 *
 * @author WangZhiYao
 * @date 2020/7/19
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

fun Int.dp2px(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(), context.resources.displayMetrics
    ).roundToInt()
}

fun Int.sp2px(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(), context.resources.displayMetrics
    ).roundToInt()
}

fun ViewGroup.emptyViewHolder(): EmptyViewHolder {
    return EmptyViewHolder(
        ItemEmptyBinding.inflate(
            LayoutInflater.from(this.context),
            this,
            false
        )
    )
}
