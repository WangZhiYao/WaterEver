package me.zhiyao.waterever.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.zhiyao.waterever.data.db.AppDatabase
import me.zhiyao.waterever.data.db.ioThread
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    private const val DB_NAME = "WaterEver.db"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        // TODO: 2020/7/13 添加初始数据
                    }
                }
            })
            .build()

    @Provides
    @Singleton
    fun providePlantDao(appDatabase: AppDatabase) =
        appDatabase.plantDao()

    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase) =
        appDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideTagDao(appDatabase: AppDatabase) =
        appDatabase.tagDao()

    @Provides
    @Singleton
    fun providePlantTagRelationDao(appDatabase: AppDatabase) =
        appDatabase.plantTagRelationDao()

    @Provides
    @Singleton
    fun provideGrowthRecordDao(appDatabase: AppDatabase) =
        appDatabase.growthRecordDao()

    @Provides
    @Singleton
    fun providePlantGrowthRecordRelationDao(appDatabase: AppDatabase) =
        appDatabase.plantGrowthRecordRelationDao()

    @Provides
    @Singleton
    fun provideImageDao(appDatabase: AppDatabase) =
        appDatabase.imageDao()
}