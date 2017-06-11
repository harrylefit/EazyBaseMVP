package vn.eazy.base.mvp.menu;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Harry on 2/1/17.
 */

public interface OnActionMenu {
    void showMenu();

    void hideMenu();

    void switchTabMenu(int pos);

    void changeColorOfMenu(int color);

    void showMenuWithAnimation();

    void bind(FragmentPagerAdapter fragmentPagerAdapter);
}
