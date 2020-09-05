package me.zhiyao.waterever.ui.plant.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.Tag
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

    private val addPlantComplete: MutableLiveData<Boolean> = MutableLiveData()

    var plantName: String? = null

    var plantFeatureImage: String? = null

    var plantCategoryId: Long? = null

    var plantTagIds: List<Long>? = null

    val categories: LiveData<List<Category>> =
        plantRepository.getCategories().asLiveData()

    val tags: LiveData<List<Tag>> =
        plantRepository.getTags().asLiveData()

    fun queryCategoryById(categoryId: Long): LiveData<Category?> =
        plantRepository.queryCategoryById(categoryId).asLiveData()

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


    fun setAddPlantComplete(complete: Boolean) {
        addPlantComplete.value = complete
    }

    fun isAddPlantComplete(): LiveData<Boolean> {
        return addPlantComplete
    }
}