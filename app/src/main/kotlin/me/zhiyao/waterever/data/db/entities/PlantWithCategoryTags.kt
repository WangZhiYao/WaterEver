package me.zhiyao.waterever.data.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
data class PlantWithCategoryTags(
    @Embedded
    val plant: Plant,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "category_id"
    )
    val category: Category?,
    @Relation(
        parentColumn = "plant_id",
        entityColumn = "tag_id",
        associateBy = Junction(PlantTagRelation::class)
    )
    val tagList: List<Tag>
)