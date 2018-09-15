package spave.levan.waterever.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import spave.levan.waterever.BuildConfig;
import spave.levan.waterever.R;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class SplashActivity extends BaseActivity {

    private static final int REQUEST_CODE_EXTERNAL_STORAGE = 9502;

    private static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private AlertDialog mRequestPermissionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission(PERMISSIONS)) {
            startMainActivity();
            return;
        }

        if (mRequestPermissionDialog == null || !mRequestPermissionDialog.isShowing()) {
            showRequestPermissionDialog(PERMISSIONS);
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean checkPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    private void showRequestPermissionDialog(String[] permissions) {
        mRequestPermissionDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(getString(R.string.dialog_permission_message))
                .setPositiveButton(getString(R.string.dialog_continue), (dialogInterface, i) -> {
                    List<String> shouldShowRequestPermissions = new ArrayList<>();
                    for (String permission : permissions) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                            shouldShowRequestPermissions.add(permission);
                        }
                    }

                    if (!shouldShowRequestPermissions.isEmpty()) {
                        ActivityCompat.requestPermissions(SplashActivity.this,
                                shouldShowRequestPermissions.toArray(new String[shouldShowRequestPermissions.size()]),
                                REQUEST_CODE_EXTERNAL_STORAGE);

                        return;
                    }

                    startSystemSetting();
                })
                .setNegativeButton(getString(R.string.dialog_exit), (dialogInterface, i) -> finish())
                .show();
    }

    private void startSystemSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", BuildConfig.APPLICATION_ID, null));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_EXTERNAL_STORAGE) {
            List<String> deniedPermissions = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        deniedPermissions.add(permissions[i]);
                    }
                }
            }

            if (!deniedPermissions.isEmpty()) {
                showRequestPermissionDialog(deniedPermissions.toArray(new String[deniedPermissions.size()]));
                return;
            }

            startMainActivity();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRequestPermissionDialog != null && mRequestPermissionDialog.isShowing()) {
            mRequestPermissionDialog.dismiss();
        }
    }
}
