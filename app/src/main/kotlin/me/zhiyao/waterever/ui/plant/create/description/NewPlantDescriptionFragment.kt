package me.zhiyao.waterever.ui.plant.create.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.constants.PlantState
import me.zhiyao.waterever.data.db.model.Plant
import me.zhiyao.waterever.databinding.FragmentNewPlantDescriptionBinding
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel

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
        binding.btnComplete.setOnClickListener {
            savePlant()
        }
    }

    private fun savePlant() {
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
                binding.root.showSnackBar(R.string.new_plant_failed, Snackbar.LENGTH_SHORT)
            } else {
                Toast.makeText(requireContext(), R.string.new_plant_success, Toast.LENGTH_SHORT)
                    .show()
                viewModel.setAddPlantComplete(true)
            }
        })
    }
}