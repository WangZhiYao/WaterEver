package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.PlantTagRelationDao
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class PlantTagRelationRepository(
    private val plantTagRelationDao: PlantTagRelationDao
) : Repository {

    suspend fun addPlantTagRelation(plantTagRelationList: List<PlantTagRelation>) {
        plantTagRelationDao.insertBatch(plantTagRelationList)
    }
}