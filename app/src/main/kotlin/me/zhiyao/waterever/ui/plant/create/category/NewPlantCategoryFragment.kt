package me.zhiyao.waterever.ui.plant.create.category

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.FragmentNewPlantCategoryBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.category.CategoriesActivity
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.ui.plant.create.category.adapter.NewPlantCategoryAdapter
import me.zhiyao.waterever.ui.widgets.SpacingItemDecoration

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantCategoryFragment : BaseFragment(), NewPlantCategoryAdapter.OnCategoryClickListener {

    companion object {
        private const val TAG = "NewPlantCategoryFragment"
    }

    private lateinit var binding: FragmentNewPlantCategoryBinding

    private val viewModel by viewModels<NewPlantCategoryViewModel>()
    private val parentViewModel by activityViewModels<NewPlantViewModel>()

    private var adapter: NewPlantCategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantCategoryBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        setHasOptionsMenu(true)

        adapter = NewPlantCategoryAdapter()
        adapter!!.setOnCategoryClickListener(this)

        binding.rvCategories.let {
            it.layoutManager = LinearLayoutManager(it.context)
            it.addItemDecoration(SpacingItemDecoration(8.dp2px(it.context)))
            it.adapter = adapter
        }

        binding.fabNext.setOnClickListener {
            it.isEnabled = false
            findNavController().navigate(R.id.action_category_to_tag)
        }
    }

    private fun initData() {
        viewModel.categories.observe(viewLifecycleOwner, { categories ->
            adapter?.setCategories(categories)
        })

        parentViewModel.plantCategoryId?.let { categoryId ->
            viewModel.queryCategoryById(categoryId).observe(viewLifecycleOwner, { category ->
                category?.run {
                    adapter?.setSelectedCategory(this)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_categories_new, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_category_new) {
            try {
                CategoriesActivity.start(requireActivity())
            } catch (ex: IllegalStateException) {
                Logger.e(TAG, ex)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCategoryClicked(selectedCategory: Category?) {
        parentViewModel.plantCategoryId = selectedCategory?.id
        binding.fabNext.setImageResource(
            if (selectedCategory == null) R.drawable.ic_menu_skip_white
            else R.drawable.ic_menu_check_white
        )
    }
}
