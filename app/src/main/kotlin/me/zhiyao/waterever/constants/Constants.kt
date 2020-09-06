package me.zhiyao.waterever.constants

import ando.file.FileOperator

/**
 *
 * @author WangZhiYao
 * @date 2020/9/6
 */
object Constants {

    const val IMAGE_AUTHORITY = "me.zhiyao.waterever.fileprovider"

    val TEMP_FEATURE_IMAGE_DIR = FileOperator.getCachePath("temp_feature_image")

    val FEATURE_IMAGE_DIR = FileOperator.getFilesPath("feature_image")
}