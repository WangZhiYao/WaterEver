package me.zhiyao.waterever.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.data.db.model.Category
import me.zhiyao.waterever.databinding.ActivityCategoriesBinding
import me.zhiyao.waterever.exts.dp2px
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseActivity
import me.zhiyao.waterever.ui.category.adapter.CategoryAdapter
import me.zhiyao.waterever.ui.widgets.SpacingItemDecoration

/**
 *
 * @author WangZhiYao
 * @date 2020/8/19
 */
@AndroidEntryPoint
class CategoriesActivity : BaseActivity(), CategoryAdapter.OnCategoryClickListener,
    CategoryAdapter.OnItemLongClickListener {

    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel by viewModels<CategoriesViewModel>()

    private lateinit var adapter: CategoryAdapter

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, CategoriesActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    override fun showBack(): Boolean = true

    private fun initView() {
        adapter = CategoryAdapter()
        adapter.setOnCategoryClickListener(this)
        adapter.setOnItemLongClickListener(this)

        binding.rvCategories.let {
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(SpacingItemDecoration(4.dp2px(this)))
            it.adapter = adapter
        }
    }

    private fun initData() {
        viewModel.categories.observe(this, { categories ->
            adapter.setCategories(categories)
            setSelectionMode(false)
        })
    }

    override fun onCategoryClicked(category: Category) {

    }

    override fun onLongClicked() {
        if (!adapter.isSelectionMode()) {
            setSelectionMode(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_categories_new, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        if (adapter.isSelectionMode()) {
            menuInflater.inflate(R.menu.menu_categories_edit, menu)
        } else {
            menuInflater.inflate(R.menu.menu_categories_new, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_category_new -> {
                showNewCategoryDialog()
                true
            }
            R.id.menu_category_delete -> {
                viewModel.deleteCategories(adapter.getSelectedItem())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showNewCategoryDialog() {
        MaterialDialog(this).show {
            title(R.string.dialog_categories_new)
            input(allowEmpty = false) { _, charSequence ->
                val category = charSequence.toString()
                if (adapter.isCategoryExist(category)) {
                    binding.root.showSnackBar(R.string.categories_already_exist)
                } else {
                    viewModel.addCategory(Category(category, System.currentTimeMillis()))
                }
            }
            positiveButton(R.string.dialog_categories_add)
        }
    }

    override fun onBackPressed() {
        if (adapter.isSelectionMode()) {
            setSelectionMode(false)
            return
        } else {
            super.onBackPressed()
        }
    }

    private fun setSelectionMode(isSelectionMode: Boolean) {
        adapter.setSelectionMode(isSelectionMode)
        invalidateOptionsMenu()
    }
}