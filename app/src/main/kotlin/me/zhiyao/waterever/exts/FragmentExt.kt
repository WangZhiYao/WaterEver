package me.zhiyao.waterever.exts

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import me.zhiyao.waterever.log.Logger

/**
 *
 * @author WangZhiYao
 * @date 2020/9/4
 */
private const val TAG = "FragmentExt"

fun Fragment.checkSelfPermissionCompat(permission: String): Int {
    return try {
        ActivityCompat.checkSelfPermission(requireActivity(), permission)
    } catch (ex: IllegalStateException) {
        Logger.e(TAG, ex)
        PackageManager.PERMISSION_DENIED
    }
}

fun Fragment.shouldShowRequestPermissionRationaleCompat(permission: String): Boolean {
    return try {
        ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permission)
    } catch (ex: IllegalStateException) {
        Logger.e(TAG, ex)
        false
    }
}

fun Fragment.requestPermissionsCompat(
    permissionsArray: Array<String>,
    requestCode: Int
) {
    try {
        this.requestPermissions(permissionsArray, requestCode)
    } catch (ex: IllegalStateException) {
        Logger.e(TAG, ex)
    }
}