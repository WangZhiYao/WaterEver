package me.zhiyao.waterever.ui.record.create.plant

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.entities.PlantWithCategoryTags
import me.zhiyao.waterever.databinding.FragmentNewGrowthRecordPlantBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantActivity
import me.zhiyao.waterever.ui.record.create.NewGrowthRecordViewModel
import me.zhiyao.waterever.ui.record.create.plant.adapter.NewGrowthRecordPlantAdapter
import me.zhiyao.waterever.ui.widgets.SpacingItemDecoration

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
@AndroidEntryPoint
class NewGrowthRecordPlantFragment : BaseFragment(),
    NewGrowthRecordPlantAdapter.OnPlantClickListener {

    companion object {
        private const val TAG = "NewGrowthRecordPlantFragment"
    }

    private lateinit var binding: FragmentNewGrowthRecordPlantBinding

    private val viewModel by viewModels<NewGrowthRecordPlantViewModel>()
    private val parentViewModel by activityViewModels<NewGrowthRecordViewModel>()

    private var adapter: NewGrowthRecordPlantAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewGrowthRecordPlantBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        setHasOptionsMenu(true)

        adapter = NewGrowthRecordPlantAdapter()
        adapter!!.setOnPlantClickListener(this)

        binding.rvPlants.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.addItemDecoration(SpacingItemDecoration(8.dp2px(it.context), true))
            it.adapter = adapter
        }

        parentViewModel.plant?.let {
            adapter!!.setSelectedPlant(it)
        }
    }

    private fun initData() {
        viewModel.plants.observe(viewLifecycleOwner, { adapter?.setPlants(it) })
    }

    override fun onPlantClicked(plantWithCategoryTags: PlantWithCategoryTags) {
        parentViewModel.plant = plantWithCategoryTags.plant
        adapter!!.setSelectedPlant(plantWithCategoryTags.plant)
        findNavController().navigate(R.id.action_plant_to_type)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_growth_record_plant_new, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_growth_record_plant_new) {
            try {
                NewPlantActivity.start(requireContext())
            } catch (ex: IllegalStateException) {
                Logger.e(TAG, ex)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}