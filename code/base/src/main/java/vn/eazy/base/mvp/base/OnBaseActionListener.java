package vn.eazy.base.mvp.base;

import vn.eazy.base.mvp.toolbar.ToolbarHelper;

/**
 * Created by Harry on 12/23/16.
 */

public interface OnBaseActionListener {
    void setTitleToolbar(String msg);

    void setTitleToolbar(String msg, String font);

    void setTitleMainColor(int color);

    void showMenu(boolean isShow);

    ToolbarHelper getToolbarHelper() throws IllegalAccessException;
}
