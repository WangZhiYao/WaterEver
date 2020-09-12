package me.zhiyao.waterever.ui.plant.create.feature

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yalantis.ucrop.UCrop
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.config.GlideApp
import me.zhiyao.waterever.constants.Constants
import me.zhiyao.waterever.constants.RequestCode
import me.zhiyao.waterever.databinding.FragmentNewPlantFeatureImageBinding
import me.zhiyao.waterever.exts.*
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.utils.PermissionManager
import java.io.File
import java.util.*

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantFeatureImageFragment : BaseFragment(), PermissionManager.OnPermissionListener {

    companion object {
        private const val TAG = "NewPlantFeatureImageFragment"
    }

    private lateinit var binding: FragmentNewPlantFeatureImageBinding

    private val viewModel by activityViewModels<NewPlantViewModel>()

    private var permissionManager: PermissionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantFeatureImageBinding.inflate(layoutInflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        binding.ivPlantFeatureImage.setOnClickListener {
            attemptToShowImageSelector()
        }
        binding.fabNext.setOnClickListener {
            it.isEnabled = false
            findNavController().navigate(R.id.action_feature_image_to_category)
        }
    }

    private fun initData() {
        viewModel.plantFeatureImage?.let {
            setFeatureImage(it)
        }
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
            .captureStrategy(CaptureStrategy(true, Constants.IMAGE_AUTHORITY))
            .maxSelectable(1)
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

    private fun openImageCrop(sourcePath: String) {
        try {
            val options = UCrop.Options()

            with(options) {
                setStatusBarColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimaryDark
                    )
                )
                setToolbarColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                setToolbarWidgetColor(Color.WHITE)
                setCircleDimmedLayer(true)
                setActiveControlsWidgetColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorAccent
                    )
                )
            }

            UCrop.of(
                Uri.fromFile(File(sourcePath)),
                Uri.fromFile(
                    File(
                        Constants.CACHE_DIR,
                        "${UUID.randomUUID().short()}.jpg"
                    )
                )
            )
                .withAspectRatio(1f, 1f)
                .withOptions(options)
                .start(requireContext(), this)
        } catch (ex: IllegalStateException) {
            Logger.e(TAG, ex)
        }
    }

    private fun setFeatureImage(imagePath: String) {
        viewModel.plantFeatureImage = imagePath
        GlideApp.with(binding.ivPlantFeatureImage)
            .load(imagePath)
            .circleCrop()
            .into(binding.ivPlantFeatureImage)
        binding.fabNext.setImageResource(R.drawable.ic_menu_check_white)
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
            Snackbar.LENGTH_INDEFINITE,
            R.string.permissions_permit
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
                        Matisse.obtainPathResult(data)?.let {
                            if (it.isNotEmpty()) {
                                openImageCrop(it[0])
                            }
                        }
                    }
                }
                RequestCode.IMAGE_CROP -> {
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            UCrop.getOutput(data)?.path?.let {
                                setFeatureImage(it)
                            }
                        }
                        UCrop.RESULT_ERROR -> {
                            val errorMessage =
                                UCrop.getError(data)?.message ?: getString(R.string.error_unknown)
                            binding.root.showSnackBar(errorMessage)
                        }
                    }
                }
            }
        }
    }
}