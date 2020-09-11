package me.zhiyao.waterever.ui.plant.create.tag

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.zhiyao.waterever.data.repo.TagRepository

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
class NewPlantTagViewModel @ViewModelInject constructor(
    private val tagRepository: TagRepository
) : ViewModel() {

    val tags = tagRepository.getTags().asLiveData()
}