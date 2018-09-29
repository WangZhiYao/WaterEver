package spave.levan.waterever.ui.activities;

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
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class SplashActivity extends BaseActivity {

    private AlertDialog mRequestPermissionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission()) {
            startMainActivity();
            return;
        }

        if (mRequestPermissionDialog == null || !mRequestPermissionDialog.isShowing()) {
            showRequestPermissionDialog(Constants.PERMISSIONS);
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : Constants.PERMISSIONS) {
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
                    List<String> shouldShowRequestPermissionList = new ArrayList<>();
                    for (String permission : permissions) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                            shouldShowRequestPermissionList.add(permission);
                        }
                    }

                    if (!shouldShowRequestPermissionList.isEmpty()) {
                        String[] shouldShowRequestPermissions = new String[shouldShowRequestPermissionList.size()];
                        ActivityCompat.requestPermissions(this,
                                shouldShowRequestPermissionList.toArray(shouldShowRequestPermissions),
                                Constants.REQUEST_CODE_EXTERNAL_STORAGE);
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
        if (requestCode == Constants.REQUEST_CODE_EXTERNAL_STORAGE) {
            List<String> deniedPermissionList = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        deniedPermissionList.add(permissions[i]);
                    }
                }
            }

            if (!deniedPermissionList.isEmpty()) {
                String[] deniedPermissions = new String[deniedPermissionList.size()];
                showRequestPermissionDialog(deniedPermissionList.toArray(deniedPermissions));
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
