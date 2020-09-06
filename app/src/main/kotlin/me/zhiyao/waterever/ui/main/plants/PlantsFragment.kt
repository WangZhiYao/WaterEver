package me.zhiyao.waterever.ui.main.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.databinding.FragmentPlantsBinding
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.main.plants.adapter.PlantAdapter

/**
 *
 * @author WangZhiYao
 * @date 2020/6/28
 */
@AndroidEntryPoint
class PlantsFragment : BaseFragment() {

    companion object {
        private const val TAG = "PlantsFragment"
    }

    private lateinit var binding: FragmentPlantsBinding

    private val viewModel by viewModels<PlantsViewModel>()

    private var adapter: PlantAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantsBinding.inflate(inflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        try {
            binding.rvPlants.layoutManager = LinearLayoutManager(requireContext())
        } catch (ex: IllegalStateException) {
            Logger.e(TAG, ex)
        }

        adapter = PlantAdapter()
        binding.rvPlants.adapter = adapter
    }

    private fun initData() {
        viewModel.plants.observe(viewLifecycleOwner, { adapter?.setPlants(it) })
    }
}