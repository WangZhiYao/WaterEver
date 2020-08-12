package me.zhiyao.waterever.data.db.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.PlantTag

/**
 *
 * @author Administrator
 * @date 2020/8/10
 */
@Entity(
    tableName = "plant_tag_relations",
    primaryKeys = [
        "plant_id",
        "plant_tag_id"
    ],
    foreignKeys = [
        ForeignKey(
            entity = Plant::class,
            parentColumns = ["plant_id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlantTag::class,
            parentColumns = ["plant_tag_id"],
            childColumns = ["plant_tag_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["plant_id"]),
        Index(value = ["plant_tag_id"]),
        Index(value = ["plant_id", "plant_tag_id"], unique = true)
    ]
)
data class PlantTagRelation(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "plant_tag_id")
    val plantTagId: Long
)