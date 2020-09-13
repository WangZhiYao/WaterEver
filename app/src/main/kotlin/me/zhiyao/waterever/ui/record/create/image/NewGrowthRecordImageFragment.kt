package me.zhiyao.waterever.ui.record.create.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import me.zhiyao.waterever.databinding.FragmentNewGrowthRecordImageBinding
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.record.create.NewGrowthRecordViewModel
import me.zhiyao.waterever.ui.record.create.image.adapter.NewGrowthRecordImageAdapter
import me.zhiyao.waterever.ui.record.create.image.viewholder.OnImageClickListener

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordImageFragment : BaseFragment(), OnImageClickListener {

    private lateinit var binding: FragmentNewGrowthRecordImageBinding

    private val parentViewModel by activityViewModels<NewGrowthRecordViewModel>()

    private var adapter: NewGrowthRecordImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentNewGrowthRecordImageBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        setHasOptionsMenu(true)

        adapter = NewGrowthRecordImageAdapter()
        adapter!!.setOnImageClickListener(this)

        binding.rvImages.let {
            it.layoutManager = GridLayoutManager(it.context, 3)
            it.adapter = adapter
        }
    }

    private fun initData() {
        parentViewModel.photoPaths?.let {
            adapter?.setImages(it)
        }
    }

    override fun onImageClicked(imagePath: String) {
        // TODO: 2020/9/13 打开PhotoView
    }

    override fun onRemoveClicked(imagePath: String) {
        adapter?.onImageRemoved(imagePath)
        parentViewModel.photoPaths?.let {
            val temp = it.toMutableList()
            temp.remove(imagePath)
            parentViewModel.photoPaths = temp
            if (temp.isEmpty()) {
                findNavController().popBackStack()
            }
        }
    }
}