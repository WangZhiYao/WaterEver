package me.zhiyao.waterever.db.dao

import androidx.room.Dao
import androidx.room.Insert
import me.zhiyao.waterever.db.model.Plant

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Dao
interface PlantDao {

    @Insert
    fun insert(plant: Plant): Long
}