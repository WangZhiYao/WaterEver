package me.zhiyao.waterever.db.dao

import androidx.room.Dao
import androidx.room.Insert
import me.zhiyao.waterever.db.model.PlantGrowthRecord

/**
 *
 * @author Administrator
 * @date 2020/7/13
 */
@Dao
interface PlantGrowthRecordDao {

    @Insert
    fun insert(plantGrowthRecord: PlantGrowthRecord): Long
}