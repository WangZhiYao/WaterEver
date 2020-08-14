package me.zhiyao.waterever.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val mViewModel by viewModels<HomeViewModel>()
    private lateinit var mBinding: FragmentHomeBinding

    private lateinit var mHomeItemAdapter: HomeItemAdapter

    override fun setRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun initView() {
        mHomeItemAdapter = HomeItemAdapter()
        mBinding.rvHome.layoutManager = LinearLayoutManager(context)
        mBinding.rvHome.adapter = mHomeItemAdapter
    }

    override fun initData() {
        mViewModel.growthRecordList.observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                mHomeItemAdapter.submitData(it)
            }
        })
    }
}