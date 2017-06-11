package vn.eazy.base.mvp.toolbar;

import android.view.View;

/**
 * Created by QuangTo on 12/24/16.
 */

public interface OnToolbarAction {
    void setTitle(String title);

    void setTitle(String title, String font);

    void setTitleMainColor(int color);

    void showToolbar(boolean isShow);

    void showLeftButton(boolean isShow);

    void showLeftButton(boolean isShow, View.OnClickListener onClickListener);

    void showLeftButton(int iconRes, View.OnClickListener onClickListener);

    void showLeftButton(String text, View.OnClickListener onClickListener);

    void showRightButton(boolean isShow);

    void showRightButton(String text, View.OnClickListener onClickListener);

    void showRightButton(int iconRes, View.OnClickListener onClickListener);
}
