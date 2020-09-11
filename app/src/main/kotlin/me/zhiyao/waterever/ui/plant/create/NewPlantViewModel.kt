package me.zhiyao.waterever.ui.plant.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation
import me.zhiyao.waterever.data.repo.PlantRepository
import me.zhiyao.waterever.data.repo.PlantTagRelationRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository,
    private val plantTagRelationRepository: PlantTagRelationRepository
) : ViewModel() {

    private val complete = MutableLiveData<Boolean>()

    var plantName: String? = null

    var plantFeatureImage: String? = null

    var plantCategoryId: Long? = null

    var plantTagIds: List<Long>? = null

    fun addPlant(plant: Plant, tagIds: List<Long>?) = liveData {
        val plantId = plantRepository.addPlant(plant)
        if (plantId != -1) {
            tagIds?.let {
                val plantTagRelationList = ArrayList<PlantTagRelation>()
                for (tagId in it) {
                    plantTagRelationList.add(PlantTagRelation(plantId, tagId))
                }
                plantTagRelationRepository.addPlantTagRelation(plantTagRelationList)
            }
        }
        emit(plantId)
    }

    fun setComplete(complete: Boolean) {
        this.complete.value = complete
    }

    fun isCompleted() = complete
}