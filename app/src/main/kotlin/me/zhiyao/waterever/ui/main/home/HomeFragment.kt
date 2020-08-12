package me.zhiyao.waterever.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentHomeBinding
import me.zhiyao.waterever.ui.base.BaseFragment

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun setRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {

    }

    override fun initData() {

    }
}