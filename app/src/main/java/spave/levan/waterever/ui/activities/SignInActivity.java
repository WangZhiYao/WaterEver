package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.RegexUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/30
 */
public class SignInActivity extends BaseActivity {

    @BindView(R.id.signIn_Email)
    EditText mSignInEmail;
    @BindView(R.id.signIn_Password)
    EditText mSignInPassword;
    @BindView(R.id.signIn_SignUp)
    TextView mSignInSignUp;
    @BindView(R.id.signIn_SignIn)
    Button mSignIn;

    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        canGoBack();

        mSignIn.setEnabled(false);
    }

    @OnTextChanged(value = R.id.signIn_Email)
    public void onEmailChanged(CharSequence charSequence) {
        mEmail = charSequence.toString();
        mSignIn.setEnabled(RegexUtils.isEmail(mEmail) && RegexUtils.isPassword(mPassword));
    }

    @OnTextChanged(value = R.id.signIn_Password)
    public void onPasswordChanged(CharSequence charSequence) {
        mPassword = charSequence.toString();
        mSignIn.setEnabled(RegexUtils.isEmail(mEmail) && RegexUtils.isPassword(mPassword));
    }

    @OnClick({R.id.signIn_SignUp, R.id.signIn_SignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signIn_SignUp:
                startActivityForResult(new Intent(this, SignUpActivity.class),
                        Constants.REQUEST_CODE_SIGN_UP);
                break;
            case R.id.signIn_SignIn:
                attemptSignIn(mEmail, mPassword);
                break;
            default:
                break;
        }
    }

    private void attemptSignIn(String email, String password) {
        BmobUser.loginByAccount(email, password, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e != null) {
                    showToast(e.getMessage());
                    return;
                }

                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_SIGN_UP && resultCode == RESULT_OK) {
            mEmail = data.getStringExtra(Constants.EXTRA_USERNAME);
            mPassword = data.getStringExtra(Constants.EXTRA_PASSWORD);
            attemptSignIn(mEmail, mPassword);
        }
    }
}
