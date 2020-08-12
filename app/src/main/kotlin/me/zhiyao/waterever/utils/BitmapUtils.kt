package me.zhiyao.waterever.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 *
 * @author WangZhiYao
 * @date 2020/7/21
 */
object BitmapUtils {

    private val COLORS = arrayOf(
        "#5CE7E6", "#12C892", "#FC76D9", "#EA27C2", "#D6394B",
        "#AE1932", "#C24CF5", "#B436F2", "#36B0D8", "#00A8FF"
    )

    fun generateFeature(text: String, radius: Int): Bitmap {
        val firstChar = if (text.isEmpty()) '#' else text[0]

        val bitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)

        val circlePaint = Paint()
        circlePaint.color = Color.parseColor(COLORS[firstChar.hashCode() % 10])
        circlePaint.isAntiAlias = true

        canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), circlePaint)

        val textPaint = Paint()
        textPaint.color = Color.WHITE
        textPaint.textSize = radius * 2 * 0.6f
        textPaint.isAntiAlias = true
        textPaint.textAlign = Paint.Align.CENTER

        val fontMetrics = textPaint.fontMetrics
        val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseline: Float = radius + distance

        canvas.drawText(firstChar.toString(), radius.toFloat(), baseline, textPaint)

        return bitmap
    }
}