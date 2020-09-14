package me.zhiyao.waterever.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.constants.GrowthRecordType
import me.zhiyao.waterever.databinding.ItemHomeChangeSoilBinding
import me.zhiyao.waterever.databinding.ItemHomeFertilizeBinding
import me.zhiyao.waterever.databinding.ItemHomePhotosBinding
import me.zhiyao.waterever.databinding.ItemHomeWateringBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.ui.main.home.entity.HomeItem
import me.zhiyao.waterever.ui.main.home.viewholder.HomeChangeSoilViewHolder
import me.zhiyao.waterever.ui.main.home.viewholder.HomeFertilizeViewHolder
import me.zhiyao.waterever.ui.main.home.viewholder.HomePhotosViewHolder
import me.zhiyao.waterever.ui.main.home.viewholder.HomeWateringViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
class HomeItemAdapter :
    PagingDataAdapter<HomeItem, RecyclerView.ViewHolder>(HomeItemComparator) {

    companion object {
        private const val VIEW_TYPE_WATERING = 1
        private const val VIEW_TYPE_CHANGE_SOIL = 2
        private const val VIEW_TYPE_FERTILIZE = 3
        private const val VIEW_TYPE_PHOTOS = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_WATERING -> return HomeWateringViewHolder(
                ItemHomeWateringBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            VIEW_TYPE_CHANGE_SOIL -> return HomeChangeSoilViewHolder(
                ItemHomeChangeSoilBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            VIEW_TYPE_FERTILIZE -> return HomeFertilizeViewHolder(
                ItemHomeFertilizeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            VIEW_TYPE_PHOTOS -> return HomePhotosViewHolder(
                ItemHomePhotosBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> return parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { homeItem ->
            when (homeItem.growthRecord.recordType) {
                GrowthRecordType.WATERING ->
                    (holder as HomeWateringViewHolder).bind(homeItem)
                GrowthRecordType.CHANGE_SOIL ->
                    (holder as HomeChangeSoilViewHolder).bind(homeItem)
                GrowthRecordType.FERTILIZE ->
                    (holder as HomeFertilizeViewHolder).bind(homeItem)
                GrowthRecordType.PHOTOS ->
                    (holder as HomePhotosViewHolder).bind(homeItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let { homeItem ->
            return when (homeItem.growthRecord.recordType) {
                GrowthRecordType.WATERING -> VIEW_TYPE_WATERING
                GrowthRecordType.CHANGE_SOIL -> VIEW_TYPE_CHANGE_SOIL
                GrowthRecordType.FERTILIZE -> VIEW_TYPE_FERTILIZE
                GrowthRecordType.PHOTOS -> VIEW_TYPE_PHOTOS
            }
        }

        return super.getItemViewType(position)
    }
}
