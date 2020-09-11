package me.zhiyao.waterever.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import kotlinx.coroutines.flow.map
import me.zhiyao.waterever.data.db.dao.GrowthRecordDao
import me.zhiyao.waterever.data.db.model.GrowthRecord
import me.zhiyao.waterever.data.mapper.HomeItemMapper

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class GrowthRecordRepository(
    private val growthRecordDao: GrowthRecordDao,
    private val homeItemMapper: HomeItemMapper
) : Repository {

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 3,
        initialLoadSize = 20,
        enablePlaceholders = false
    )

    fun getHomeItemList() =
        Pager(pagingConfig) {
            growthRecordDao.plantGrowthRecordList()
        }.flow.map { pagingData ->
            pagingData.map { plantGrowthRecord ->
                homeItemMapper.map(plantGrowthRecord)
            }
        }

    suspend fun addGrowthRecord(growthRecord: GrowthRecord) =
        growthRecordDao.insert(growthRecord)
}