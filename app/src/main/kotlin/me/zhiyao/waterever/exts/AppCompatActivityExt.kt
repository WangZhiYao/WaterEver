package me.zhiyao.waterever.exts

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 *
 * @author WangZhiYao
 * @date 2020/8/18
 */
fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionsCompat(
    permissionsArray: Array<String>,
    requestCode: Int
) {
    ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
}
