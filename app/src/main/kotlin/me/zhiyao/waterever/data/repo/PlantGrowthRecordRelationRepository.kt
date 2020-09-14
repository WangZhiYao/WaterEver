package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.PlantGrowthRecordRelationDao
import me.zhiyao.waterever.data.db.model.relations.PlantGrowthRecordRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class PlantGrowthRecordRelationRepository(
    private val plantGrowthRecordRelationDao: PlantGrowthRecordRelationDao
) : Repository {

    suspend fun addPlantGrowthRecordRelation(plantGrowthRecordRelation: PlantGrowthRecordRelation) {
        plantGrowthRecordRelationDao.insert(plantGrowthRecordRelation)
    }
}