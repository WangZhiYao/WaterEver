package me.zhiyao.waterever.ui.plant.create.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantCategoryViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    val categories: LiveData<List<Category>> =
        plantRepository.getCategories().asLiveData()

    fun queryCategoryById(categoryId: Long): LiveData<Category?> =
        plantRepository.queryCategoryById(categoryId).asLiveData()
}