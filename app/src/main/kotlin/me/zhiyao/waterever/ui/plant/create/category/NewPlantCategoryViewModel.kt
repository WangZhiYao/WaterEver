package me.zhiyao.waterever.ui.plant.create.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.zhiyao.waterever.data.repo.CategoryRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantCategoryViewModel @ViewModelInject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val categories = categoryRepository.getCategories().asLiveData()

    fun queryCategoryById(categoryId: Long) =
        categoryRepository.queryCategoryById(categoryId).asLiveData()
}