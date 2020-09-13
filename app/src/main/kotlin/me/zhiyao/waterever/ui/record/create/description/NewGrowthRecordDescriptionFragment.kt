package me.zhiyao.waterever.ui.record.create.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import me.zhiyao.waterever.R
import me.zhiyao.waterever.constants.GrowthRecordType
import me.zhiyao.waterever.data.db.model.GrowthRecord
import me.zhiyao.waterever.databinding.FragmentNewGrowthRecordDescriptionBinding
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.record.create.NewGrowthRecordViewModel

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordDescriptionFragment : BaseFragment() {

    private lateinit var binding: FragmentNewGrowthRecordDescriptionBinding

    private val parentViewModel by activityViewModels<NewGrowthRecordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentNewGrowthRecordDescriptionBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.fabComplete.setOnClickListener {
            binding.fabComplete.isEnabled = false
            saveGrowthRecord()
        }
    }

    private fun saveGrowthRecord() {
        val growthRecord = GrowthRecord(
            parentViewModel.plant!!.id,
            parentViewModel.growthRecordType!!,
            binding.etNewGrowthRecordDescription.text.toString(),
            System.currentTimeMillis()
        )

        parentViewModel.addGrowthRecord(
            growthRecord,
            if (parentViewModel.growthRecordType == GrowthRecordType.PHOTOS) parentViewModel.photoPaths
            else null
        ).observe(viewLifecycleOwner, { growthRecordId ->
            if (growthRecordId == -1L) {
                binding.root.showSnackBar(R.string.new_growth_record_failed)
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.new_growth_record_success,
                    Toast.LENGTH_SHORT
                )
                    .show()
                parentViewModel.setComplete(true)
            }
        })
    }
}