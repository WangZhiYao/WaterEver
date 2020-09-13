package me.zhiyao.waterever.constants

import me.zhiyao.waterever.BuildConfig
import me.zhiyao.waterever.utils.StorageHelper

/**
 *
 * @author WangZhiYao
 * @date 2020/9/6
 */
object Constants {

    const val APP_NAME = "WaterEver"

    const val IMAGE_AUTHORITY = "${BuildConfig.APPLICATION_ID}.fileprovider"

    val CACHE_DIR = StorageHelper.getCacheDir()

    val FEATURE_IMAGE_DIR = StorageHelper.getFileDir("feature_image")
}