package me.zhiyao.waterever.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(tableName = "categories")
data class Category(
    val name: String,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var id: Long = 0
}