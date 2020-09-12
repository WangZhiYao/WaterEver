package me.zhiyao.waterever.ui.tag

import android.content.Context
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
import me.zhiyao.waterever.data.db.model.Tag
import me.zhiyao.waterever.databinding.ActivityTagsBinding
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseActivity
import me.zhiyao.waterever.ui.tag.adapter.TagAdapter

/**
 *
 * @author WangZhiYao
 * @date 2020/8/27
 */
@AndroidEntryPoint
class TagsActivity : BaseActivity(), TagAdapter.OnItemLongClickListener {

    private lateinit var binding: ActivityTagsBinding

    private val viewModel by viewModels<TagsViewModel>()

    private lateinit var adapter: TagAdapter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TagsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTagsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    override fun showBack(): Boolean = true

    private fun initView() {
        binding.rvTags.layoutManager = LinearLayoutManager(this)

        adapter = TagAdapter()
        adapter.setOnItemLongClickListener(this)

        binding.rvTags.adapter = adapter
    }

    private fun initData() {
        viewModel.tags.observe(this, { tags ->
            adapter.setTags(tags)
            setSelectionMode(false)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tags_new, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        if (adapter.isSelectionMode()) {
            menuInflater.inflate(R.menu.menu_tags_edit, menu)
        } else {
            menuInflater.inflate(R.menu.menu_tags_new, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_tag_new -> {
                showNewTagDialog()
                true
            }
            R.id.menu_tag_delete -> {
                viewModel.deleteTags(adapter.getSelectedItem())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showNewTagDialog() {
        MaterialDialog(this).show {
            title(R.string.dialog_tags_new)
            input(allowEmpty = false) { _, charSequence ->
                val tagName = charSequence.toString()
                if (adapter.isTagExist(tagName)) {
                    binding.root.showSnackBar(R.string.tags_already_exist)
                } else {
                    viewModel.addTag(Tag(tagName, System.currentTimeMillis()))
                }
            }
            positiveButton(R.string.dialog_tags_add)
        }
    }

    override fun onLongClicked() {
        if (!adapter.isSelectionMode()) {
            setSelectionMode(true)
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