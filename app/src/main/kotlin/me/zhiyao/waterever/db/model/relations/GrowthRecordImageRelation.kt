package me.zhiyao.waterever.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 *
 * @author WangZhiYao
 * @date 2020/7/14
 */
@Entity(
    tableName = "record_image_relation",
    primaryKeys = [
        "growth_record_id",
        "image_id"
    ]
)
data class GrowthRecordImageRelation(
    @ColumnInfo(name = "growth_record_id")
    val growthRecordId: Long,
    @ColumnInfo(name = "image_id", index = true)
    val imageId: Long
)