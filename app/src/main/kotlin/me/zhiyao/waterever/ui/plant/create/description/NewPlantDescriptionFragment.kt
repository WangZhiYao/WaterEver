package me.zhiyao.waterever.ui.plant.create.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.constants.Constants
import me.zhiyao.waterever.constants.PlantState
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.databinding.FragmentNewPlantDescriptionBinding
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.utils.StorageHelper
import java.io.File

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantDescriptionFragment : BaseFragment() {

    private lateinit var binding: FragmentNewPlantDescriptionBinding

    private val viewModel by activityViewModels<NewPlantViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantDescriptionBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.fabComplete.setOnClickListener {
            it.isEnabled = false
            savePlant()
        }
    }

    private fun savePlant() {
        viewModel.plantFeatureImage?.let { imagePath ->
            val fileName = StorageHelper.getFileNameFromPath(imagePath)
            if (!StorageHelper.copyFile(
                    File(imagePath),
                    File(Constants.FEATURE_IMAGE_DIR, fileName!!)
                )
            ) {
                binding.root.showSnackBar(R.string.new_plant_failed)
                return
            } else {
                viewModel.plantFeatureImage =
                    File(Constants.FEATURE_IMAGE_DIR, fileName).absolutePath
                StorageHelper.delete(imagePath)
            }
        }

        val plant = Plant(
            viewModel.plantName!!,
            PlantState.ALIVE,
            viewModel.plantFeatureImage,
            viewModel.plantCategoryId,
            binding.etNewPlantDescription.text.toString(),
            System.currentTimeMillis()
        )

        viewModel.addPlant(plant, viewModel.plantTagIds).observe(viewLifecycleOwner, { plantId ->
            if (plantId == -1L) {
                binding.root.showSnackBar(R.string.new_plant_failed)
            } else {
                Toast.makeText(requireContext(), R.string.new_plant_success, Toast.LENGTH_SHORT)
                    .show()
                viewModel.setComplete(true)
            }
        })
    }
}