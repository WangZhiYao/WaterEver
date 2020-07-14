package me.zhiyao.waterever.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.zhiyao.waterever.db.dao.*
import me.zhiyao.waterever.db.model.*

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@Database(
    entities = [
        Plant::class,
        Category::class,
        PlantCategory::class,
        PlantRecord::class,
        Reminder::class,
        ReminderPeriod::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun categoryDao(): CategoryDao

    abstract fun plantCategoryDao(): PlantCategoryDao

    abstract fun plantRecordDao(): PlantRecordDao

    abstract fun reminderDao(): ReminderDao

    abstract fun reminderPeriodDao(): ReminderPeriodDao
}