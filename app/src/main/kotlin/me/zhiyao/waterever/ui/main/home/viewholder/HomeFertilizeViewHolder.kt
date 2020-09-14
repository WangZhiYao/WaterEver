package me.zhiyao.waterever.ui.main.home.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.config.GlideApp
import me.zhiyao.waterever.config.GlideRequest
import me.zhiyao.waterever.databinding.ItemHomeFertilizeBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.ui.main.home.entity.HomeItem
import me.zhiyao.waterever.utils.BitmapUtils
import me.zhiyao.waterever.utils.DateUtils

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
class HomeFertilizeViewHolder(
    private val binding: ItemHomeFertilizeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(homeItem: HomeItem) {
        homeItem.plant.let { plant ->
            binding.ivPlantFeatureImage.run {
                val requests = GlideApp.with(this)
                val request: GlideRequest<Drawable>
                request = if (plant.featureImage.isNullOrBlank()) {
                    requests.load(
                        BitmapUtils.generateFeature(
                            plant.name,
                            56.dp2px(binding.root.context)
                        )
                    )
                } else {
                    requests.load(plant.featureImage)
                        .circleCrop()
                }

                request.into(this)
            }

            binding.tvPlantName.text = plant.name

            binding.tvPlantCreateTime.let {
                it.text = it.context.getString(
                    R.string.plant_create_time_prefix,
                    DateUtils.toDateString(plant.createTime, DateUtils.PATTERN_YYYY_MM_DD)
                )
            }
        }

        homeItem.growthRecord.let { growthRecord ->
            binding.tvPlantLastRecord.text =
                DateUtils.toTimeInterval(
                    binding.tvPlantLastRecord.context,
                    System.currentTimeMillis() - growthRecord.createTime
                )
            if (!growthRecord.description.isNullOrBlank()) {
                binding.tvPlantDescription.visibility = View.VISIBLE
                binding.tvPlantDescription.text = growthRecord.description
            } else {
                binding.tvPlantDescription.visibility = View.GONE
            }
        }
    }
}