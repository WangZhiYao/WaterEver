package me.zhiyao.waterever.ui.record.create.plant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.databinding.ItemNewGrowthRecordPlantBinding
import me.zhiyao.waterever.exts.emptyViewHolder
import me.zhiyao.waterever.exts.tipsViewHolder
import me.zhiyao.waterever.ui.record.create.plant.viewholder.NewGrowthRecordPlantViewHolder
import me.zhiyao.waterever.ui.widgets.TipsViewHolder

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordPlantAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_PLANT = 1
        private const val VIEW_TYPE_EMPTY = 2
    }

    private var plants: List<PlantWithCategoryTags>? = null

    private var onPlantClickListener: OnPlantClickListener? = null

    private var selectedPlant: Plant? = null

    fun setPlants(plants: List<PlantWithCategoryTags>) {
        this.plants = plants
        notifyDataSetChanged()
    }

    fun setOnPlantClickListener(onPlantClickListener: OnPlantClickListener) {
        this.onPlantClickListener = onPlantClickListener
    }

    fun setSelectedPlant(plant: Plant) {
        this.selectedPlant = plant
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PLANT -> NewGrowthRecordPlantViewHolder(
                ItemNewGrowthRecordPlantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_EMPTY -> parent.tipsViewHolder()
            else -> parent.emptyViewHolder()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_PLANT -> {
                plants?.get(position)?.let { plantWithCategoryTags ->
                    (holder as NewGrowthRecordPlantViewHolder).bind(
                        plantWithCategoryTags,
                        selectedPlant == plantWithCategoryTags.plant
                    )
                    holder.itemView.setOnClickListener {
                        onPlantClickListener?.onPlantClicked(plantWithCategoryTags)
                    }
                }
            }
            VIEW_TYPE_EMPTY -> {
                (holder as TipsViewHolder).bind(
                    holder.itemView.context.getString(R.string.new_growth_record_plant_empty_notice)
                )
            }
        }
    }

    override fun getItemCount(): Int =
        if (plants.isNullOrEmpty()) 1 else plants!!.size

    override fun getItemViewType(position: Int): Int =
        if (plants.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_PLANT

    interface OnPlantClickListener {

        fun onPlantClicked(plantWithCategoryTags: PlantWithCategoryTags)
    }
}