package me.zhiyao.waterever.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.zhiyao.waterever.constants.GrowthRecordType

/**
 *
 * @author WangZhiYao
 * @date 2020/8/10
 */
@Entity(tableName = "growth_records")
data class GrowthRecord(
    @ColumnInfo(name = "growth_record_type")
    val type: GrowthRecordType,
    var description: String?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "growth_record_id")
    var id: Long = 0
}