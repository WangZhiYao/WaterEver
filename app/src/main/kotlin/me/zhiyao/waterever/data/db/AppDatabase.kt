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
        Image::class,
        GrowthRecord::class,
        Category::class,
        Tag::class,
        PlantTagRelation::class,
        Reminder::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    abstract fun imageDao(): ImageDao

    abstract fun growthRecordDao(): GrowthRecordDao

    abstract fun categoryDao(): CategoryDao

    abstract fun tagDao(): TagDao

    abstract fun plantTagRelationshipDao(): PlantTagRelationDao

    abstract fun reminderDao(): ReminderDao
}