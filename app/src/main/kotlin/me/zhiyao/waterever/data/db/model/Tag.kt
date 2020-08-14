package me.zhiyao.waterever.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(tableName = "tags")
data class Tag(
    val name: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var id: Long = 0
}