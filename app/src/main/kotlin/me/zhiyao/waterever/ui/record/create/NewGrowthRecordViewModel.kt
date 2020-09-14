package me.zhiyao.waterever.ui.record.create

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.zhiyao.waterever.constants.GrowthRecordType
import me.zhiyao.waterever.data.db.model.GrowthRecord
import me.zhiyao.waterever.data.db.model.Image
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.data.db.model.relations.PlantGrowthRecordRelation
import me.zhiyao.waterever.data.repo.GrowthRecordRepository
import me.zhiyao.waterever.data.repo.ImageRepository
import me.zhiyao.waterever.data.repo.PlantGrowthRecordRelationRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordViewModel @ViewModelInject constructor(
    private val growthRecordRepository: GrowthRecordRepository,
    private val plantGrowthRecordRelationRepository: PlantGrowthRecordRelationRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val complete = MutableLiveData<Boolean>()

    var plant: Plant? = null

    var growthRecordType: GrowthRecordType? = null

    var photoPaths: List<String>? = null

    var description: String? = null

    fun addGrowthRecord(plant: Plant, growthRecord: GrowthRecord, photoPaths: List<String>?) =
        liveData {
            val growthRecordId = growthRecordRepository.addGrowthRecord(growthRecord)
            if (growthRecordId != -1) {
                val plantGrowthRecordRelation =
                    PlantGrowthRecordRelation(plant.id, growthRecordId)
                plantGrowthRecordRelationRepository.addPlantGrowthRecordRelation(
                    plantGrowthRecordRelation
                )

                photoPaths?.let {
                    val images = ArrayList<Image>()
                    for (photoPath in it) {
                        images.add(Image(growthRecordId, photoPath, System.currentTimeMillis()))
                    }
                    imageRepository.addImages(images)
                }
            }
            emit(growthRecordId)
        }

    fun setComplete(complete: Boolean) {
        this.complete.value = complete
    }

    fun isCompleted() = complete
}