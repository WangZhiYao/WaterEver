package me.zhiyao.waterever.exts

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
fun Int.dp2px(context: Context) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(), context.resources.displayMetrics
    ).roundToInt()

fun Int.sp2px(context: Context) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(), context.resources.displayMetrics
    ).roundToInt()
