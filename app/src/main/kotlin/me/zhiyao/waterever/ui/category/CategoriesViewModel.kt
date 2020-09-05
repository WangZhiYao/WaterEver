package me.zhiyao.waterever.ui.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class CategoriesViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    val categories: LiveData<List<Category>> =
        plantRepository.getCategories().asLiveData()

    fun addCategory(category: Category) {
        viewModelScope.launch {
            plantRepository.addCategory(category)
        }
    }

    fun deleteCategories(categories: List<Category>) {
        viewModelScope.launch {
            plantRepository.deleteCategories(categories)
        }
    }
}