package me.zhiyao.waterever.ui.main.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentPlantBinding
import me.zhiyao.waterever.ui.base.BaseFragment

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class PlantFragment : BaseFragment() {

    private val viewModel by viewModels<PlantViewModel>()
    private lateinit var binding: FragmentPlantBinding

    override fun setRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {

    }

    override fun initData() {

    }
}