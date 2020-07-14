package me.zhiyao.waterever.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @date 2020/7/13
 */
@Entity(tableName = "plant_record")
data class PlantRecord(

    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    val type: Int,
    val amount: Int?,
    @Embedded
    val image: Image?,
    @ColumnInfo(name = "create_time")
    val createTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_record_id")
    var id: Long = 0

    data class Image(
        val path: String,
        val note: String?
    )
}