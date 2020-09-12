package me.zhiyao.waterever.ui.plant.create.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.FragmentNewPlantNameBinding
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantNameFragment : BaseFragment() {

    private lateinit var binding: FragmentNewPlantNameBinding

    private val viewModel by activityViewModels<NewPlantViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantNameBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        binding.etNewPlantName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                next()
                true
            } else {
                false
            }
        }

        binding.etNewPlantName.doAfterTextChanged {
            binding.fabNext.isEnabled = !it.isNullOrBlank()
            binding.etNewPlantName.error = if (!it.isNullOrBlank()) {
                null
            } else {
                getString(R.string.new_plant_name_can_not_be_empty)
            }
        }

        binding.fabNext.setOnClickListener { next() }
    }

    private fun initData() {
        viewModel.plantName?.let {
            binding.etNewPlantName.setText(it)
        }
    }

    private fun next() {
        if (binding.etNewPlantName.text.isNullOrBlank()) {
            binding.etNewPlantName.requestFocus()
            binding.etNewPlantName.error = getString(R.string.new_plant_name_can_not_be_empty)
        } else {
            binding.fabNext.isEnabled = false
            viewModel.plantName = binding.etNewPlantName.text.toString()
            findNavController().navigate(R.id.action_name_to_feature_image)
        }
    }
}