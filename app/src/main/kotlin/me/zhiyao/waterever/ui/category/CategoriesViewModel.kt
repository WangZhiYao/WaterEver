package me.zhiyao.waterever.ui.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.data.repo.CategoryRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
class CategoriesViewModel @ViewModelInject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val categories: LiveData<List<Category>> =
        categoryRepository.getCategories().asLiveData()

    fun addCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.addCategory(category)
        }
    }

    fun deleteCategories(categories: List<Category>) {
        viewModelScope.launch {
            categoryRepository.deleteCategories(categories)
        }
    }
}