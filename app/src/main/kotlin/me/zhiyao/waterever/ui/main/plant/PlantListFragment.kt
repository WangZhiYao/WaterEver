package me.zhiyao.waterever.ui.main.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentPlantListBinding
import me.zhiyao.waterever.ui.base.BaseFragment

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class PlantListFragment : BaseFragment() {

    private lateinit var binding: FragmentPlantListBinding

    private val viewModel by viewModels<PlantListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)
        return binding.root
    }
}