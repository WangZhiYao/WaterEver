package me.zhiyao.waterever.ui.plant.create.tag

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantTagViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    val tags: LiveData<List<Tag>> =
        plantRepository.getTags().asLiveData()
}