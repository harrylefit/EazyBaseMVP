package vn.eazy.base.mvp.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import vn.eazy.base.mvp.R;


/**
 * Created by Harry on 2/16/17.
 */

public abstract class BaseDialog extends Dialog {
    public View dialogContent;

    public BaseDialog(Context context) {
        super(context, R.style.AppTheme_Dialog);

    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
    }

    public abstract int getLayoutId();
}
