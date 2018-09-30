package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.RegexUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/30
 */
public class SignUpActivity extends BaseActivity {

    @BindView(R.id.signUp_Email)
    EditText mSignUpEmail;
    @BindView(R.id.signUp_Password)
    EditText mSignUpPassword;
    @BindView(R.id.signUp_SignUp)
    Button mSignUp;

    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        canGoBack();

        mSignUp.setEnabled(false);
    }

    @OnTextChanged(value = R.id.signUp_Email)
    public void onEmailChanged(CharSequence charSequence) {
        mEmail = charSequence.toString();
        mSignUp.setEnabled(RegexUtils.isEmail(mEmail) && RegexUtils.isPassword(mPassword));
    }

    @OnTextChanged(value = R.id.signUp_Password)
    public void onPasswordChanged(CharSequence charSequence) {
        mPassword = charSequence.toString();
        mSignUp.setEnabled(RegexUtils.isEmail(mEmail) && RegexUtils.isPassword(mPassword));
    }

    @OnClick(R.id.signUp_SignUp)
    public void onViewClicked() {
        mEmail = mSignUpEmail.getText().toString().trim();
        mPassword = mSignUpPassword.getText().toString().trim();

        BmobUser user = new BmobUser();
        user.setUsername(mEmail);
        user.setPassword(mPassword);
        user.setEmail(mEmail);
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e != null) {
                    showToast(e.getMessage());
                    return;
                }

                setResult(RESULT_OK, new Intent().putExtra(Constants.EXTRA_USERNAME, mEmail)
                        .putExtra(Constants.EXTRA_PASSWORD, mPassword));

                finish();
            }
        });
    }
}
