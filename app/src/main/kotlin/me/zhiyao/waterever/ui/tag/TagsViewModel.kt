package me.zhiyao.waterever.ui.tag

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.data.repo.TagRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/27
 */
class TagsViewModel @ViewModelInject constructor(
    private val tagRepository: TagRepository
) : ViewModel() {

    val tags = tagRepository.getTags().asLiveData()

    fun addTag(tag: Tag) {
        viewModelScope.launch {
            tagRepository.addTag(tag)
        }
    }

    fun deleteTags(tags: List<Tag>) {
        viewModelScope.launch {
            tagRepository.deleteTags(tags)
        }
    }
}