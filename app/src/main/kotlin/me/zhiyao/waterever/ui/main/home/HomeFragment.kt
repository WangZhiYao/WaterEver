package me.zhiyao.waterever.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentHomeBinding
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.main.home.adapter.HomeItemAdapter

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var adapter: HomeItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)

        adapter = HomeItemAdapter()
        binding.rvHome.adapter = adapter
    }

    private fun initData() {
        viewModel.growthRecordList.observe(this, { homeItemList ->
            lifecycleScope.launchWhenCreated {
                adapter.submitData(homeItemList)
            }
        })
    }
}