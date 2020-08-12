package me.zhiyao.waterever.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.zhiyao.waterever.data.db.dao.*
import me.zhiyao.waterever.data.db.model.*
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation

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
        PlantTag::class,
        PlantTagRelation::class,
        PlantImage::class,
        PlantReminder::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun plantGrowthRecordDao(): GrowthRecordDao

    abstract fun plantCategoryDao(): CategoryDao

    abstract fun plantTagDao(): TagDao

    abstract fun plantTagRelationshipDao(): PlantTagRelationshipDao

    abstract fun plantImageDao(): ImageDao

    //abstract fun plantReminderDao(): ReminderDao
}