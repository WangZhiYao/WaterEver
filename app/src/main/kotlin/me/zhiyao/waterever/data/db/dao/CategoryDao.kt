package me.zhiyao.waterever.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.zhiyao.waterever.data.db.model.Category

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM categories ORDER BY create_time DESC")
    fun queryAll(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE category_id = :categoryId")
    fun queryById(categoryId: Long): Flow<Category?>
}