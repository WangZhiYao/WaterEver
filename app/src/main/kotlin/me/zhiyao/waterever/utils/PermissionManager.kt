package me.zhiyao.waterever.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import me.zhiyao.waterever.exts.checkSelfPermissionCompat
import me.zhiyao.waterever.exts.requestPermissionsCompat
import me.zhiyao.waterever.exts.shouldShowRequestPermissionRationaleCompat
import me.zhiyao.waterever.log.Logger

/**
 *
 * @author WangZhiYao
 * @date 2020/9/6
 */
class PermissionManager private constructor(private val host: Any) {

    companion object {
        private const val TAG = "PermissionManager"

        private const val MAP_KEY_DENY = "deny"
        private const val MAP_KEY_RATIONALE = "rationale"

        fun with(activity: Activity): PermissionManager {
            return PermissionManager(activity)
        }

        fun with(fragment: Fragment): PermissionManager {
            return PermissionManager(fragment)
        }
    }

    private var permissions: Array<out String>? = null
    private var requestCode: Int? = null
    private var onPermissionListener: OnPermissionListener? = null

    private var positive: Boolean = false

    fun permissions(vararg permissions: String): PermissionManager {
        this.permissions = permissions
        return this
    }

    fun requestCode(requestCode: Int): PermissionManager {
        this.requestCode = requestCode
        return this
    }

    fun setOnPermissionListener(onPermissionListener: OnPermissionListener): PermissionManager {
        this.onPermissionListener = onPermissionListener
        return this
    }

    fun request() {
        if (!permissions.isNullOrEmpty() && requestCode != null) {
            request(permissions!!.toMutableList(), requestCode!!)
        }
    }

    private fun request(permissions: MutableList<String>, requestCode: Int) {
        val activity = getActivity(host) ?: return
        val map = findDeniedPermissions(activity, permissions)
        val deniedPermissions = map[MAP_KEY_DENY]
        val rationales = map[MAP_KEY_RATIONALE]

        if (deniedPermissions!!.isNotEmpty()) {
            if (rationales!!.isNotEmpty() && !positive) {
                onPermissionListener?.onShowRationale(rationales)
                return
            }

            when (host) {
                is Activity -> {
                    host.requestPermissionsCompat(deniedPermissions.toTypedArray(), requestCode)
                }
                is Fragment -> {
                    host.requestPermissionsCompat(deniedPermissions.toTypedArray(), requestCode)
                }
                else -> {
                    throw IllegalArgumentException("${host::class.java.name} is not supported")
                }
            }
        } else {
            onPermissionListener?.onGranted()
        }
    }

    fun onPermissionResult(permissions: MutableList<String>, results: IntArray) {
        val deniedPermissions = ArrayList<String>()
        for ((index, result) in results.withIndex()) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[index])
            }
        }
        if (deniedPermissions.isNotEmpty()) {
            onPermissionListener?.onDenied()
        } else {
            onPermissionListener?.onGranted()
        }
    }

    private fun findDeniedPermissions(
        activity: Activity,
        permissions: MutableList<String>
    ): Map<String, List<String>> {
        val map = HashMap<String, List<String>>()
        val deniedPermissions = ArrayList<String>()
        val rationales = ArrayList<String>()

        for (permission in permissions) {
            if (activity.checkSelfPermissionCompat(permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission)
                if (shouldShowRequestPermissionRationale(permission)) {
                    rationales.add(permission)
                }
            }
        }

        map[MAP_KEY_DENY] = deniedPermissions
        map[MAP_KEY_RATIONALE] = rationales

        return map
    }

    private fun getActivity(any: Any): Activity? {
        return when (any) {
            is Fragment -> {
                try {
                    return any.requireActivity()
                } catch (ex: IllegalStateException) {
                    Logger.e(TAG, ex)
                }
                return null
            }
            is Activity -> {
                any
            }
            else -> {
                null
            }
        }
    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return when (host) {
            is Activity -> {
                host.shouldShowRequestPermissionRationaleCompat(permission)
            }
            is Fragment -> {
                host.shouldShowRequestPermissionRationaleCompat(permission)
            }
            else -> {
                throw IllegalArgumentException("${host::class.java.name} is not supported")
            }
        }
    }

    interface OnPermissionListener {

        fun onGranted()

        fun onDenied()

        fun onShowRationale(permissions: List<String>)
    }
}