package spave.levan.waterever.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import spave.levan.waterever.R;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

    }
}
