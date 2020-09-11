package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.PlantDao
import me.zhiyao.waterever.data.db.model.Plant

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
class PlantRepository(
    private val plantDao: PlantDao
) : Repository {

    fun getAllAlivePlants() = plantDao.queryAllAlivePlants()

    suspend fun addPlant(plant: Plant) = plantDao.insert(plant)
}