package vn.eazy.base.mvp.toolbar;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import vn.eazy.base.mvp.R;

/**
 * Created by Harry on 12/24/16.
 */

public class ToolbarHelper implements OnToolbarAction {
    protected Toolbar toolbar;
    protected AppCompatTextView leftBtn;
    protected AppCompatTextView tvTitle;
    protected AppCompatTextView rightBtn;
    protected Context context;

    public ToolbarHelper(Toolbar toolbar) throws IllegalAccessException {
        this.toolbar = toolbar;
        context = toolbar.getContext();
        leftBtn = (AppCompatTextView) toolbar.findViewById(R.id.left_button);
        rightBtn = (AppCompatTextView) toolbar.findViewById(R.id.right_button);
        if (rightBtn == null) {
            throw new IllegalAccessException("Can't find this right button.");
        } else {
            rightBtn.setClickable(true);
        }
        if (leftBtn == null) {
            throw new IllegalAccessException("Can't find this Left button");
        } else {
            leftBtn.setClickable(true);
        }
        tvTitle = (AppCompatTextView) toolbar.findViewById(R.id.tvTitleToolbar);
        if (tvTitle == null) {
            throw new IllegalAccessException("Can't find this Title TextView");
        } else {
            tvTitle.setClickable(true);
        }
    }

    @Override
    public void setTitle(@NonNull String title) {
        if (toolbar != null)
            setTitle(title, "");
    }

    @Override
    public void setTitle(@NonNull String title, @NonNull String font) {
        tvTitle.setText(title);
        if (!TextUtils.isEmpty(font)) {
            tvTitle.setTypeface(Typeface.createFromAsset(tvTitle.getContext().getAssets(), font));
        }
    }

    @Override
    public void setTitleMainColor(int color) {
        if (toolbar != null)
            tvTitle.setTextColor(color);
    }

    @Override
    public void showToolbar(boolean isShow) {
        if (toolbar != null)
            if (isShow) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
    }

    @Override
    public void showLeftButton(boolean isShow) {
        showLeftButton(isShow, null);
    }

    @Override
    public void showLeftButton(boolean isShow, View.OnClickListener onClickListener) {
        leftBtn.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        if (onClickListener != null) {
            leftBtn.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void showLeftButton(@Nullable int iconRes, View.OnClickListener onClickListener) {
        leftBtn.setText("");
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, iconRes), null, null, null);
        leftBtn.setOnClickListener(onClickListener);
        showLeftButton(true);
    }

    @Override
    public void showLeftButton(@Nullable String text, View.OnClickListener onClickListener) {
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        leftBtn.setText(Html.fromHtml(text));
        leftBtn.setOnClickListener(onClickListener);
        showLeftButton(true);
    }


    @Override
    public void showRightButton(boolean isShow) {
        rightBtn.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showRightButton(@Nullable String text, View.OnClickListener onClickListener) {
        rightBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        rightBtn.setText(Html.fromHtml(text));
        rightBtn.setOnClickListener(onClickListener);
    }

    @Override
    public void showRightButton(@Nullable int iconRes, View.OnClickListener onClickListener) {
        rightBtn.setText("");
        rightBtn.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, iconRes), null, null, null);
        rightBtn.setOnClickListener(onClickListener);
    }

    public AppCompatTextView getLeftBtn() {
        return leftBtn;
    }

    public AppCompatTextView getTvTitle() {
        return tvTitle;
    }

    public AppCompatTextView getRightBtn() {
        return rightBtn;
    }
}
