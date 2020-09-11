package me.zhiyao.waterever.ui.plant.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.relations.PlantTagRelation
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val complete: MutableLiveData<Boolean> = MutableLiveData()

    var plantName: String? = null

    var plantFeatureImage: String? = null

    var plantCategoryId: Long? = null

    var plantTagIds: List<Long>? = null

    fun addPlant(plant: Plant, tagIds: List<Long>?): LiveData<Long?> = liveData {
        val plantId = plantRepository.addPlant(plant)
        if (plantId != -1) {
            tagIds?.let {
                val plantTagRelationList = ArrayList<PlantTagRelation>()
                for (tagId in it) {
                    plantTagRelationList.add(PlantTagRelation(plantId, tagId))
                }
                plantRepository.addPlantTagRelation(plantTagRelationList)
            }
        }
        emit(plantId)
    }

    fun setComplete(complete: Boolean) {
        this.complete.value = complete
    }

    fun isCompleted(): LiveData<Boolean> {
        return complete
    }
}