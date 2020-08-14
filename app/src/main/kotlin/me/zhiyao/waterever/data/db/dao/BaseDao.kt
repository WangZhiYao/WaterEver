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
    fun insert(arg: T): Long

    @Update
    fun update(arg: T)

    @Delete
    fun delete(arg: T)
}