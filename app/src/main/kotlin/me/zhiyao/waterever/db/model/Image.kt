package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
@Entity(
    tableName = "image"
)
data class Image(
    val path: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    var imageId: Long = 0
}