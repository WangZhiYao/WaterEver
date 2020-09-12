package me.zhiyao.waterever

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import me.zhiyao.waterever.utils.StorageHelper

/**
 *
 * @author WangZhiYao
 * @date 2020/6/27
 */
@HiltAndroidApp
class WaterEverApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StorageHelper.init(this)
    }
}