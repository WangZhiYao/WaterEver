package me.zhiyao.waterever.utils

import android.content.Context
import android.os.Environment
import me.zhiyao.waterever.exts.extClose
import me.zhiyao.waterever.log.Logger
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
object StorageHelper {

    private const val TAG = "StorageHelper"

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    private val isExternalStorageMounted =
        Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    fun getCacheDir(dirName: String): File =
        File(getCacheDir(), dirName)

    fun getCacheDir(): File =
        if (isExternalStorageMounted && context.externalCacheDir != null
        ) context.externalCacheDir!!
        else context.cacheDir

    fun getFileDir(dirName: String): File =
        File(getFileDir(), dirName)

    fun getFileDir(): File =
        if (isExternalStorageMounted && context.getExternalFilesDir(null) != null
        ) context.getExternalFilesDir(null)!!
        else context.filesDir

    fun getFileNameFromPath(path: String): String? {
        if (path.isBlank()) {
            return null
        }
        val cut = path.lastIndexOf('/')
        if (cut != -1) {
            return path.substring(cut + 1)
        }
        return path
    }

    fun copyFile(src: File, dest: File): Boolean {
        var srcChannel: FileChannel? = null
        var destChannel: FileChannel? = null
        return try {
            if (!src.exists()) {
                return false
            }
            if (dest.exists()) {
                dest.delete()
            }

            val parent = dest.parentFile
            if (parent != null && !parent.exists()) {
                parent.mkdirs()
            }

            dest.createNewFile()

            srcChannel = FileInputStream(src).channel
            destChannel = FileOutputStream(dest).channel

            srcChannel.transferTo(0, srcChannel.size(), destChannel)

            true
        } catch (ex: Exception) {
            Logger.e(TAG, ex)
            false
        } finally {
            srcChannel?.extClose()
            destChannel?.extClose()
        }
    }

    fun delete(path: String): Boolean {
        val file = File(path)
        return try {
            file.delete()
        } catch (ex: Exception) {
            Logger.e(TAG, ex)
            false
        }
    }
}