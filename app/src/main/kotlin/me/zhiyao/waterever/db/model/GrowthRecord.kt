package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(
    tableName = "growth_record"
)
data class GrowthRecord(
    val type: Int,
    val remark: String?,
    val amount: Int?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "growth_record_id")
    var growthRecordId: Long = 0
}