package me.zhiyao.waterever.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import me.zhiyao.waterever.data.db.entities.PlantGrowthRecord
import me.zhiyao.waterever.data.db.model.GrowthRecord

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Dao
interface GrowthRecordDao : BaseDao<GrowthRecord> {

    @Transaction
    @Query("SELECT * FROM growth_records ORDER BY create_time DESC")
    fun plantGrowthRecordList(): PagingSource<Int, PlantGrowthRecord>
}