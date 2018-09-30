package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import spave.levan.waterever.BuildConfig;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.PermissionListener;
import spave.levan.waterever.utils.PermissionManager;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class SplashActivity extends BaseActivity {

    private PermissionManager mPermissionManager;
    private AlertDialog mRequestPermissionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestPermission(Constants.PERMISSIONS);
    }

    private void requestPermission(String... permissions) {
        mPermissionManager = PermissionManager.with(this)
                .addRequestCode(Constants.REQUEST_CODE_EXTERNAL_STORAGE)
                .permissions(permissions)
                .setPermissionsListener(new PermissionListener() {
                    @Override
                    public void onGranted() {
                        //startMainActivity();
                        Toast.makeText(SplashActivity.this, "onGranted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied() {
                        startSystemSetting();
                    }

                    @Override
                    public void onShowRationale(String[] permissions) {
                        showRequestPermissionDialog();
                    }
                })
                .request();
    }

    private void startMainActivity() {
        if (BmobUser.getCurrentUser() == null) {
            startActivity(new Intent(this, SignUpActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    private void showRequestPermissionDialog() {
        mRequestPermissionDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(getString(R.string.dialog_permission_message))
                .setPositiveButton(getString(R.string.dialog_continue), (dialogInterface, i) -> {
                    mPermissionManager.setIsPositive(true);
                    mPermissionManager.request();
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.REQUEST_CODE_EXTERNAL_STORAGE:
                mPermissionManager.onPermissionResult(permissions, grantResults);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRequestPermissionDialog != null && mRequestPermissionDialog.isShowing()) {
            mRequestPermissionDialog.dismiss();
        }
    }
}
