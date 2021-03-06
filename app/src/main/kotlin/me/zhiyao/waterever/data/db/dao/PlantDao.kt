package me.zhiyao.waterever.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.data.db.model.Plant

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Dao
interface PlantDao : BaseDao<Plant> {

    @Transaction
    @Query("SELECT * FROM plants WHERE plant_state = 1 ORDER BY create_time DESC")
    fun queryAllAlivePlants(): Flow<List<PlantWithCategoryTags>>
}