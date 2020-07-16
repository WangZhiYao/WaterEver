package me.zhiyao.waterever.db.dao

import androidx.room.Dao
import androidx.room.Insert
import me.zhiyao.waterever.db.model.relations.PlantCategoryRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/7/16
 */
@Dao
interface PlantCategoryRelationDao {

    @Insert
    fun insert(plantCategoryRelation: PlantCategoryRelation)
}