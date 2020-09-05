package me.zhiyao.waterever.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
interface BaseDao<T> {

    @Insert
    suspend fun insert(arg: T): Long

    @Insert
    suspend fun insertBatch(arg: List<T>)

    @Update
    suspend fun update(arg: T)

    @Delete
    suspend fun delete(arg: T)

    @Delete
    suspend fun deleteBatch(arg: List<T>)
}