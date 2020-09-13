package me.zhiyao.waterever.ui.record.create.type

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import me.zhiyao.waterever.R
import me.zhiyao.waterever.constants.Constants
import me.zhiyao.waterever.constants.GrowthRecordType
import me.zhiyao.waterever.constants.RequestCode
import me.zhiyao.waterever.databinding.FragmentNewGrowthRecordTypeBinding
import me.zhiyao.waterever.exts.checkSelfPermissionCompat
import me.zhiyao.waterever.exts.requestPermissionsCompat
import me.zhiyao.waterever.exts.shouldShowRequestPermissionRationaleCompat
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.record.create.NewGrowthRecordViewModel
import me.zhiyao.waterever.utils.PermissionManager

/**
 *
 * @author WangZhiYao
 * @date 2020/9/11
 */
class NewGrowthRecordTypeFragment : BaseFragment(), PermissionManager.OnPermissionListener {

    companion object {
        private const val TAG = "NewGrowthRecordTypeFragment"
    }

    private lateinit var binding: FragmentNewGrowthRecordTypeBinding

    private val parentViewModel by activityViewModels<NewGrowthRecordViewModel>()

    private var permissionManager: PermissionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewGrowthRecordTypeBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        binding.tvNewGrowthRecordTypeWatering.setOnClickListener { nextStep(GrowthRecordType.WATERING) }
        binding.tvNewGrowthRecordTypeChangeSoil.setOnClickListener { nextStep(GrowthRecordType.CHANGE_SOIL) }
        binding.tvNewGrowthRecordTypeFertilize.setOnClickListener { nextStep(GrowthRecordType.FERTILIZE) }
        binding.tvNewGrowthRecordTypePhotos.setOnClickListener { nextStep(GrowthRecordType.PHOTOS) }
    }

    private fun initData() {
        parentViewModel.growthRecordType?.run {
            setSelectedType(this)
        }
    }

    private fun nextStep(growthRecordType: GrowthRecordType) {
        setSelectedType(growthRecordType)
        parentViewModel.growthRecordType = growthRecordType
        when (growthRecordType) {
            GrowthRecordType.WATERING ->
                findNavController().navigate(R.id.action_type_to_description)
            GrowthRecordType.CHANGE_SOIL ->
                findNavController().navigate(R.id.action_type_to_description)
            GrowthRecordType.FERTILIZE ->
                findNavController().navigate(R.id.action_type_to_description)
            GrowthRecordType.PHOTOS -> attemptToShowImageSelector()
        }
    }

    private fun setSelectedType(growthRecordType: GrowthRecordType) {
        binding.tvNewGrowthRecordTypeWatering.setBackgroundColor(
            if (growthRecordType == GrowthRecordType.WATERING) Color.LTGRAY else Color.WHITE
        )
        binding.tvNewGrowthRecordTypeChangeSoil.setBackgroundColor(
            if (growthRecordType == GrowthRecordType.CHANGE_SOIL) Color.LTGRAY else Color.WHITE
        )
        binding.tvNewGrowthRecordTypeFertilize.setBackgroundColor(
            if (growthRecordType == GrowthRecordType.FERTILIZE) Color.LTGRAY else Color.WHITE
        )
        binding.tvNewGrowthRecordTypePhotos.setBackgroundColor(
            if (growthRecordType == GrowthRecordType.PHOTOS) Color.LTGRAY else Color.WHITE
        )
    }

    private fun attemptToShowImageSelector() {
        if (checkSelfPermissionCompat(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            && checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            openImageSelector()
        } else if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.CAMERA)
            || shouldShowRequestPermissionRationaleCompat(Manifest.permission.READ_EXTERNAL_STORAGE)
        ) {
            requestPermission()
        } else {
            binding.root.showSnackBar(
                R.string.external_storage_required,
                Snackbar.LENGTH_SHORT,
                R.string.permissions_permit
            ) {
                requestPermission()
            }
        }
    }

    private fun openImageSelector() {
        Matisse.from(this)
            .choose(MimeType.ofImage())
            .capture(true)
            .captureStrategy(CaptureStrategy(true, Constants.IMAGE_AUTHORITY, Constants.APP_NAME))
            .maxSelectable(9)
            .spanCount(3)
            .theme(R.style.MatisseStyle)
            .imageEngine(GlideEngine())
            .forResult(RequestCode.IMAGE_SELECTION)
    }

    private fun requestPermission() {
        permissionManager = PermissionManager.with(this)
            .permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .requestCode(RequestCode.READ_WRITE_EXTERNAL_STORAGE)
            .setOnPermissionListener(this)

        permissionManager!!.request()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionManager?.onPermissionResult(permissions.toMutableList(), grantResults)
    }

    override fun onGranted() {
        openImageSelector()
    }

    override fun onDenied() {
        binding.root.showSnackBar(R.string.permissions_denied)
    }

    override fun onShowRationale(permissions: List<String>) {
        binding.root.showSnackBar(
            R.string.external_storage_required,
            Snackbar.LENGTH_INDEFINITE, R.string.permissions_permit
        ) {
            requestPermissionsCompat(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                RequestCode.READ_WRITE_EXTERNAL_STORAGE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                RequestCode.IMAGE_SELECTION -> {
                    if (resultCode == Activity.RESULT_OK) {
                        Matisse.obtainPathResult(data).let {
                            if (!it.isNullOrEmpty()) {
                                parentViewModel.photoPaths = it
                                findNavController().navigate(R.id.action_type_to_image)
                            }
                        }
                    }
                }
            }
        }
    }
}