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
import me.zhiyao.waterever.db.AppDatabase
import me.zhiyao.waterever.db.dao.*
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
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    // TODO: 2020/7/13 添加初始数据
                }
            })
            .build()
    }

    @Provides
    fun providePlantDao(database: AppDatabase): PlantDao {
        return database.plantDao()
    }

    @Provides
    fun providePlantCategoryDao(database: AppDatabase): PlantCategoryDao {
        return database.plantCategoryDao()
    }

    @Provides
    fun provideGrowthRecordDao(database: AppDatabase): PlantGrowthRecordDao {
        return database.plantGrowthRecordDao()
    }

    @Provides
    fun provideImageDao(database: AppDatabase): PlantImageDao {
        return database.plantImageDao()
    }

    @Provides
    fun provideReminderDao(database: AppDatabase): ReminderDao {
        return database.reminderDao()
    }

    @Provides
    fun provideReminderPeriodDao(database: AppDatabase): ReminderPeriodDao {
        return database.reminderPeriodDao()
    }
}