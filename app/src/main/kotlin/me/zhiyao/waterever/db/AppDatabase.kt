package me.zhiyao.waterever.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.zhiyao.waterever.db.dao.*
import me.zhiyao.waterever.db.model.*
import me.zhiyao.waterever.db.model.relations.GrowthRecordImageRelation
import me.zhiyao.waterever.db.model.relations.PlantCategoryRelation
import me.zhiyao.waterever.db.model.relations.PlantGrowthRecordRelation
import me.zhiyao.waterever.db.model.relations.ReminderPeriodRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@Database(
    entities = [
        Plant::class,
        PlantCategory::class,
        GrowthRecord::class,
        Image::class,
        Reminder::class,
        ReminderPeriod::class,
        PlantCategoryRelation::class,
        PlantGrowthRecordRelation::class,
        GrowthRecordImageRelation::class,
        ReminderPeriodRelation::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun plantCategoryDao(): PlantCategoryDao

    abstract fun growthRecordDao(): GrowthRecordDao

    abstract fun imageDao(): ImageDao

    abstract fun reminderDao(): ReminderDao

    abstract fun reminderPeriodDao(): ReminderPeriodDao

    abstract fun plantCategoryRelationDao(): PlantCategoryRelationDao

    abstract fun plantGrowthRecordRelationDao(): PlantGrowthRecordRelationDao

    abstract fun growthRecordImageRelationDao(): GrowthRecordImageRelationDao

    abstract fun reminderPeriodRelationDao(): ReminderPeriodRelationDao
}