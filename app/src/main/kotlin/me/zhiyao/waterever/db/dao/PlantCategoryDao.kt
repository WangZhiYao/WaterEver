package me.zhiyao.waterever.db.dao

import androidx.room.Dao
import androidx.room.Insert
import me.zhiyao.waterever.db.model.PlantCategory

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Dao
interface PlantCategoryDao {

    @Insert
    fun insert(plantCategory: PlantCategory): Long
}