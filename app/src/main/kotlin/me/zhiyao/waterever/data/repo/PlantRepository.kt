package me.zhiyao.waterever.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.zhiyao.waterever.data.db.dao.*
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation
import me.zhiyao.waterever.data.mapper.HomeItemMapper
import me.zhiyao.waterever.ui.main.home.entity.HomeItem

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
class PlantRepository(
    private val plantDao: PlantDao,
    private val categoryDao: CategoryDao,
    private val tagDao: TagDao,
    private val growthRecordDao: GrowthRecordDao,
    private val plantTagRelationDao: PlantTagRelationDao,
    private val homeItemMapper: HomeItemMapper
) : Repository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    fun getHomeItemList(): Flow<PagingData<HomeItem>> {
        return Pager(pagingConfig) {
            growthRecordDao.plantGrowthRecordList()
        }.flow.map { pagingData ->
            pagingData.map { plantGrowthRecord ->
                homeItemMapper.map(plantGrowthRecord)
            }
        }
    }

    fun getAllAlivePlants(): Flow<List<PlantWithCategoryTags>> {
        return plantDao.queryAllAlivePlants()
    }

    fun getCategories(): Flow<List<Category>> {
        return categoryDao.queryAll()
    }

    suspend fun addCategory(category: Category): Long {
        return categoryDao.insert(category)
    }

    suspend fun deleteCategories(categories: List<Category>) {
        categoryDao.deleteBatch(categories)
    }

    fun queryCategoryById(categoryId: Long): Flow<Category?> {
        return categoryDao.queryById(categoryId)
    }

    fun getTags(): Flow<List<Tag>> {
        return tagDao.queryAll()
    }

    suspend fun addTag(tag: Tag): Long {
        return tagDao.insert(tag)
    }

    suspend fun deleteTags(tags: List<Tag>) {
        tagDao.deleteBatch(tags)
    }

    fun queryTagsById(tagIds: List<Long>): Flow<List<Tag>> {
        return tagDao.queryByIds(tagIds)
    }

    suspend fun addPlant(plant: Plant): Long {
        return plantDao.insert(plant)
    }

    suspend fun addPlantTagRelation(plantTagRelationList: List<PlantTagRelation>) {
        plantTagRelationDao.insertBatch(plantTagRelationList)
    }
}