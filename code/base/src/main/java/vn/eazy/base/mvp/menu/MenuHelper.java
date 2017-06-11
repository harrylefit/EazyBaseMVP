package vn.eazy.base.mvp.menu;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Harry on 2/1/17.
 */

public class MenuHelper<T extends OnActionMenu> implements OnActionMenu {
    private T menu;

    public MenuHelper(T menu) {
        this.menu = menu;
    }

    @Override
    public void showMenu() {
        this.menu.showMenu();
    }

    @Override
    public void hideMenu() {
        this.menu.hideMenu();
    }

    @Override
    public void switchTabMenu(int pos) {
        this.menu.switchTabMenu(pos);
    }

    @Override
    public void changeColorOfMenu(int color) {
        this.menu.changeColorOfMenu(color);
    }

    @Override
    public void showMenuWithAnimation() {
        this.menu.showMenuWithAnimation();
    }

    @Override
    public void bind(FragmentPagerAdapter fragmentPagerAdapter) {
        menu.bind(fragmentPagerAdapter);
    }
}
