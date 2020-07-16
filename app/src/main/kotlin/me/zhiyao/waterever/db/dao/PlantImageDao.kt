package me.zhiyao.waterever.db.dao

import androidx.room.Dao
import androidx.room.Insert
import me.zhiyao.waterever.db.model.PlantImage

/**
 *
 * @author WangZhiYao
 * @date 2020/7/15
 */
@Dao
interface PlantImageDao {

    @Insert
    fun insert(plantImage: PlantImage): Long
}