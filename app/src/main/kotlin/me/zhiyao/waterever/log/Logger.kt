package me.zhiyao.waterever.log

import android.util.Log
import me.zhiyao.waterever.constants.Constants
import java.io.PrintWriter
import java.io.StringWriter

/**
 *
 * @author WangZhiYao
 * @date 2020/9/4
 */
object Logger {

    private const val MAX_LOG_SEGMENT_LENGTH = 2000

    fun v(tag: String, msg: String) = log(Log.VERBOSE, tag, msg)

    fun d(tag: String, msg: String) = log(Log.DEBUG, tag, msg)

    fun i(tag: String, msg: String) = log(Log.INFO, tag, msg)

    fun w(tag: String, msg: String) = log(Log.WARN, tag, msg)

    fun e(tag: String, msg: String) = log(Log.ERROR, tag, msg)

    fun e(tag: String, t: Throwable) {
        try {
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            t.printStackTrace(pw)
            log(Log.ERROR, tag, sw.toString())
        } catch (e: java.lang.Exception) {
            log(Log.ASSERT, Constants.APP_NAME, t.toString())
        }
    }

    private fun log(level: Int, tag: String, msg: String) {
        var tempMsg = msg
        val sb = StringBuilder()
        sb.append("[").append(tag)
        try {
            val ex = Exception()
            val stackTrace = ex.stackTrace[2]
            val methodName = stackTrace.methodName
            sb.append(".").append(methodName)
        } catch (ex: Exception) {
            log(Log.ASSERT, Constants.APP_NAME, ex.toString())
        } finally {
            sb.append("]").append(" ").append(tempMsg)
        }
        tempMsg = sb.toString()
        val strLength = tempMsg.length
        var start = 0
        val segmentSize: Int = MAX_LOG_SEGMENT_LENGTH
        var logContent: String
        while (start < strLength) {
            logContent = if (tempMsg.length <= start + segmentSize) {
                tempMsg.substring(start)
            } else {
                tempMsg.substring(start, start + segmentSize)
            }
            start += segmentSize
            log(level, logContent)
        }
    }

    private fun log(level: Int, msg: String) {
        when (level) {
            Log.VERBOSE -> Log.v(Constants.APP_NAME, msg)
            Log.DEBUG -> Log.d(Constants.APP_NAME, msg)
            Log.INFO -> Log.i(Constants.APP_NAME, msg)
            Log.WARN -> Log.w(Constants.APP_NAME, msg)
            Log.ERROR -> Log.e(Constants.APP_NAME, msg)
            Log.ASSERT -> Log.wtf(Constants.APP_NAME, msg)
            else -> Log.wtf(Constants.APP_NAME, msg)
        }
    }
}