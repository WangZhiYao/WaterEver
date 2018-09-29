package spave.levan.waterever.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.StringUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/30
 */
public class ProgressDialog extends Dialog {

    @BindView(R.id.dialog_Title)
    TextView mTvTitle;
    @BindView(R.id.dialog_Message)
    TextView mTvMessage;

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
        init(context);
    }

    private void init(Context context) {
        View progressDialogView = LayoutInflater.from(context)
                .inflate(R.layout.layout_dialog_progress, null);
        setContentView(progressDialogView);

        ButterKnife.bind(this, progressDialogView);

        mTvTitle.setVisibility(View.GONE);
        mTvMessage.setVisibility(View.GONE);

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics dm = context.getResources().getDisplayMetrics();

            lp.width = Double.valueOf(dm.widthPixels * 0.8).intValue();
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            dialogWindow.setAttributes(lp);
        }
    }

    public void setTitle(String title) {
        if (StringUtils.isNullOrEmpty(title)) {
            return;
        }

        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
    }

    public void setMessage(String message) {
        if (StringUtils.isNullOrEmpty(message)) {
            return;
        }

        mTvMessage.setVisibility(View.VISIBLE);
        mTvMessage.setText(message);
    }
}
