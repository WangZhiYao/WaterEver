package me.zhiyao.waterever.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.zhiyao.waterever.data.db.model.Tag

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Dao
interface TagDao : BaseDao<Tag> {

    @Query("SELECT * FROM tags ORDER BY create_time DESC")
    fun queryAll(): Flow<List<Tag>>

    @Query("SELECT * FROM tags WHERE tag_id IN (:tagIds)")
    fun queryByIds(tagIds: List<Long>): Flow<List<Tag>>
}