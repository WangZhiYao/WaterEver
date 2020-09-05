package me.zhiyao.waterever.ui.plant.create.tag

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.FragmentNewPlantTagBinding
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.ui.plant.create.tag.adapter.NewPlantTagAdapter
import me.zhiyao.waterever.ui.tag.TagsActivity

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantTagFragment : BaseFragment(), NewPlantTagAdapter.OnTagClickListener {

    companion object {
        private const val TAG = "NewPlantTagFragment"
    }

    private lateinit var binding: FragmentNewPlantTagBinding

    private val viewModel by activityViewModels<NewPlantViewModel>()

    private var adapter: NewPlantTagAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantTagBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        setHasOptionsMenu(true)

        try {
            binding.rvNewPlantTag.layoutManager = LinearLayoutManager(requireContext())
        } catch (ex: IllegalStateException) {
            Logger.e(TAG, ex)
        }

        adapter = NewPlantTagAdapter()
        adapter!!.setOnTagClickListener(this)

        binding.rvNewPlantTag.adapter = adapter

        binding.btnNext.setOnClickListener {
            adapter?.selectedList?.let { tagIds ->
                viewModel.plantTagIds = tagIds
            }

            findNavController().navigate(R.id.action_tag_to_description)
        }
    }

    private fun initData() {
        viewModel.tags.observe(viewLifecycleOwner, { tags ->
            adapter?.setTags(tags)
        })
        viewModel.plantTagIds?.let {
            adapter?.selectedList = it.toMutableList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_tags_new, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_tag_new) {
            try {
                TagsActivity.start(this)
            } catch (ex: IllegalStateException) {
                Logger.e(TAG, ex)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTagClicked(tagIds: List<Long>) {
        binding.btnNext.setText(
            if (tagIds.isEmpty()) R.string.new_plant_tag_skip else R.string.new_plant_tag_next_step
        )
    }
}