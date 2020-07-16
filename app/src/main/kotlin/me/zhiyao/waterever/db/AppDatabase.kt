package me.zhiyao.waterever.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.zhiyao.waterever.db.converters.PlantGrowthRecordTypeConverter
import me.zhiyao.waterever.db.converters.PlantStateConverter
import me.zhiyao.waterever.db.converters.ReminderPeriodTypeConverter
import me.zhiyao.waterever.db.converters.ReminderTypeConverter
import me.zhiyao.waterever.db.dao.*
import me.zhiyao.waterever.db.model.*
import me.zhiyao.waterever.db.model.relations.PlantCategoryRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@Database(
    entities = [
        Plant::class,
        PlantCategory::class,
        PlantGrowthRecord::class,
        PlantImage::class,
        Reminder::class,
        ReminderPeriod::class,
        PlantCategoryRelation::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    PlantStateConverter::class,
    PlantGrowthRecordTypeConverter::class,
    ReminderTypeConverter::class,
    ReminderPeriodTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun plantCategoryDao(): PlantCategoryDao

    abstract fun plantGrowthRecordDao(): PlantGrowthRecordDao

    abstract fun plantImageDao(): PlantImageDao

    abstract fun reminderDao(): ReminderDao

    abstract fun reminderPeriodDao(): ReminderPeriodDao

    abstract fun plantCategoryRelationDao(): PlantCategoryRelationDao

}