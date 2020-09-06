package me.zhiyao.waterever

import ando.file.FileOperator
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *
 * @author WangZhiYao
 * @date 2020/6/27
 */
@HiltAndroidApp
class WaterEverApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FileOperator.init(this, BuildConfig.DEBUG)
    }
}