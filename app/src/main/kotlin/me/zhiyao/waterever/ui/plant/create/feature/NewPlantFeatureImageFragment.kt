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
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.yalantis.ucrop.UCrop
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import dagger.hilt.android.AndroidEntryPoint
import me.zhiyao.waterever.R
import me.zhiyao.waterever.constants.RequestCode
import me.zhiyao.waterever.databinding.FragmentNewPlantFeatureImageBinding
import me.zhiyao.waterever.exts.checkSelfPermissionCompat
import me.zhiyao.waterever.exts.requestPermissionsCompat
import me.zhiyao.waterever.exts.shouldShowRequestPermissionRationaleCompat
import me.zhiyao.waterever.exts.showSnackBar
import me.zhiyao.waterever.log.Logger
import me.zhiyao.waterever.ui.base.BaseFragment
import me.zhiyao.waterever.ui.plant.create.NewPlantViewModel
import me.zhiyao.waterever.utils.CoilImageEngine
import java.io.File

/**
 *
 * @author WangZhiYao
 * @date 2020/9/3
 */
@AndroidEntryPoint
class NewPlantFeatureImageFragment : BaseFragment() {

    companion object {
        private const val TAG = "NewPlantFeatureImageFragment"
    }

    private lateinit var binding: FragmentNewPlantFeatureImageBinding

    private val viewModel by activityViewModels<NewPlantViewModel>()

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
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_feature_image_to_category)
        }
    }

    private fun initData() {
        viewModel.plantFeatureImage?.let {
            setFeatureImage(it)
        }
    }

    private fun attemptToShowImageSelector() {
        if (checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            openImageSelector()
        } else {
            requestExternalStoragePermission()
        }
    }

    private fun openImageSelector() {
        Matisse.from(this)
            .choose(MimeType.ofImage())
            .maxSelectable(1)
            .spanCount(3)
            .theme(R.style.MatisseStyle)
            .imageEngine(CoilImageEngine())
            .forResult(RequestCode.IMAGE_SELECTION)
    }

    private fun requestExternalStoragePermission() {
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            binding.root.showSnackBar(
                R.string.external_storage_required,
                Snackbar.LENGTH_INDEFINITE, R.string.permissions_permit
            ) {
                requestPermissionsCompat(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    RequestCode.READ_WRITE_EXTERNAL_STORAGE
                )
            }
        } else {
            binding.root.showSnackBar(
                R.string.external_storage_not_available,
                Snackbar.LENGTH_SHORT
            )
            requestPermissionsCompat(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RequestCode.READ_WRITE_EXTERNAL_STORAGE
            )
        }
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
                Uri.fromFile(File(requireContext().cacheDir, "plant_feature_temp"))
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
        binding.ivPlantFeatureImage.load(File(imagePath)) {
            transformations(CircleCropTransformation())
        }
        binding.btnNext.setText(R.string.new_plant_feature_image_next_step)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == RequestCode.READ_WRITE_EXTERNAL_STORAGE) {
            if (checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                openImageSelector()
            } else {
                binding.root.showSnackBar(R.string.permissions_denied, Snackbar.LENGTH_SHORT)
            }
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
                            binding.root.showSnackBar(
                                errorMessage,
                                Snackbar.LENGTH_SHORT
                            )
                        }
                    }
                }
            }
        }
    }
}