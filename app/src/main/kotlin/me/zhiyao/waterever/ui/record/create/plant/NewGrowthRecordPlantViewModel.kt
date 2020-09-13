package me.zhiyao.waterever.ui.record.create.plant

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordPlantViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    val plants: LiveData<List<PlantWithCategoryTags>> =
        plantRepository.getAllAlivePlants().asLiveData()
}