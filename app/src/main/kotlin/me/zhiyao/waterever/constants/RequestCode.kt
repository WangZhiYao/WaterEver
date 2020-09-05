package me.zhiyao.waterever.constants

import com.yalantis.ucrop.UCrop

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
object RequestCode {

    const val READ_WRITE_EXTERNAL_STORAGE = 10001

    const val IMAGE_SELECTION = 20001
    const val IMAGE_CROP = UCrop.REQUEST_CROP
    const val CATEGORY_SELECTION = 20002
    const val TAG_SELECTION = 20003
}