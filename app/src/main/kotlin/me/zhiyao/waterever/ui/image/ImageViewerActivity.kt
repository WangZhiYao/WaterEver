package me.zhiyao.waterever.ui.image

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import me.zhiyao.waterever.constants.ExtraKey
import me.zhiyao.waterever.databinding.ActivityImageViewerBinding
import me.zhiyao.waterever.ui.base.BaseActivity
import me.zhiyao.waterever.ui.image.adapter.ImageViewerAdapter

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
class ImageViewerActivity : BaseActivity() {

    private lateinit var binding: ActivityImageViewerBinding

    private var adapter: ImageViewerAdapter? = null

    companion object {
        fun start(
            context: Context,
            images: List<String>,
            index: Int,
            compat: ActivityOptionsCompat?
        ) {
            val intent = Intent(context, ImageViewerActivity::class.java)
            intent.putStringArrayListExtra(ExtraKey.IMAGES, ArrayList(images))
            intent.putExtra(ExtraKey.IMAGE_INDEX, index)
            context.startActivity(intent, compat?.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    override fun showBack() = false

    private fun initView() {
        adapter = ImageViewerAdapter()
        binding.rvImages.let {
            it.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            it.adapter = adapter
            PagerSnapHelper().attachToRecyclerView(it)
        }
    }

    private fun initData() {
        intent.getStringArrayListExtra(ExtraKey.IMAGES)?.let {
            adapter?.setImages(it)
            var index = intent.getIntExtra(ExtraKey.IMAGE_INDEX, 0)
            index = if (index >= it.size) 0 else index
            binding.rvImages.scrollToPosition(index)
        }
    }
}