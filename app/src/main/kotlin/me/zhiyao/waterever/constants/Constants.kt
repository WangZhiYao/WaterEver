package me.zhiyao.waterever.constants

import ando.file.FileOperator

/**
 *
 * @author WangZhiYao
 * @date 2020/9/6
 */
object Constants {

    const val IMAGE_AUTHORITY = "me.zhiyao.waterever.fileprovider"

    val CACHE_DIR = FileOperator.getCacheDir()

    const val TEMP_FEATURE_IMAGE_NAME = "temp_feature_image.jpg"

    val FEATURE_IMAGE_DIR = FileOperator.getFilesPath("feature_image")
}