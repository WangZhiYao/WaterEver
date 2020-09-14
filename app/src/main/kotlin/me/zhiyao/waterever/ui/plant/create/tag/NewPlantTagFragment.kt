package me.zhiyao.waterever.ui.plant.create.tag

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.databinding.FragmentNewPlantTagBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.ui.plant.create.tag.adapter.NewPlantTagAdapter
import me.zhiyao.waterever.ui.tag.TagsActivity
import me.zhiyao.waterever.ui.widgets.SpacingItemDecoration

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

    private val viewModel by viewModels<NewPlantTagViewModel>()
    private val parentViewModel by activityViewModels<NewPlantViewModel>()

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

        adapter = NewPlantTagAdapter()
        adapter!!.setOnTagClickListener(this)

        binding.rvNewPlantTag.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.addItemDecoration(SpacingItemDecoration(4.dp2px(it.context)))
            it.adapter = adapter
        }

        binding.fabNext.setOnClickListener {
            it.isEnabled = false

            adapter?.selectedList?.let { tagIds ->
                parentViewModel.plantTagIds = tagIds
            }

            findNavController().navigate(R.id.action_tag_to_description)
        }
    }

    private fun initData() {
        viewModel.tags.observe(viewLifecycleOwner, { tags ->
            adapter?.setTags(tags)
        })
        parentViewModel.plantTagIds?.let {
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
                TagsActivity.start(requireContext())
            } catch (ex: IllegalStateException) {
                Logger.e(TAG, ex)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTagClicked(tagIds: List<Long>) {
        binding.fabNext.setImageResource(
            if (tagIds.isEmpty()) R.drawable.ic_menu_skip_white
            else R.drawable.ic_menu_check_white
        )
    }
}