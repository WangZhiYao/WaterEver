package me.zhiyao.waterever.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import me.zhiyao.waterever.data.repo.PlantRepository
import me.zhiyao.waterever.ui.main.home.entity.HomeItem

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
class HomeViewModel @ViewModelInject constructor(
    plantRepository: PlantRepository
) : ViewModel() {

    val growthRecordList: LiveData<PagingData<HomeItem>> =
        plantRepository.getHomeItemList()
            .cachedIn(viewModelScope)
            .asLiveData()
}