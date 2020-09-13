package me.zhiyao.waterever.ui.record.create.plant.viewholder

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.config.GlideApp
import me.zhiyao.waterever.config.GlideRequest
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.databinding.ItemNewGrowthRecordPlantBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.utils.BitmapUtils

/**
 *
 * @author WangZhiYao
 * @date 2020/9/5
 */
class NewGrowthRecordPlantViewHolder(
    private val binding: ItemNewGrowthRecordPlantBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(plantWithCategoryTags: PlantWithCategoryTags, selected: Boolean) {
        val plant = plantWithCategoryTags.plant

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


        val category = plantWithCategoryTags.category

        binding.tvPlantCategory.run {
            if (category == null) {
                visibility = View.GONE
                text = null
            } else {
                visibility = View.VISIBLE
                text = category.name
            }
        }

        binding.root.setBackgroundColor(if (selected) Color.LTGRAY else Color.WHITE)
    }
}