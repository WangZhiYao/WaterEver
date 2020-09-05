package me.zhiyao.waterever.ui.tag

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.data.repo.PlantRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/27
 */
class TagsViewModel @ViewModelInject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    val tags = plantRepository.getTags().asLiveData()

    fun addTag(tag: Tag) {
        viewModelScope.launch {
            plantRepository.addTag(tag)
        }
    }

    fun deleteTags(tags: List<Tag>) {
        viewModelScope.launch {
            plantRepository.deleteTags(tags)
        }
    }
}