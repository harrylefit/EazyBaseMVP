package vn.eazy.base.mvp.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

import vn.eazy.base.mvp.base.fragment.BaseFragment;


/**
 * Created by Harry on 12/24/16.
 */

public interface OnFragmentAction {
    void replaceFragment(int id, BaseFragment baseFragment);

    void replaceFragment(int id, BaseFragment baseFragment, Bundle bundle);

    void replaceFragment(BaseFragment baseFragment);
    void replaceFragment(BaseFragment baseFragment, boolean isBackStack, int InAnim, int OutAnim);

    void replaceFragment(BaseFragment baseFragment, Bundle bundle);

    void replaceFragment(int id, Fragment fragment);
    void replaceFragment(int id, Fragment fragment, boolean isBackStack);

    void replaceFragmentWithSharedElement(BaseFragment baseFragment, List<View> views);

    void addFragment(BaseFragment baseFragment);

    void addFragment(BaseFragment baseFragment, boolean isBackStack);

    void addFragmentWithSharedElement(BaseFragment baseFragment, List<View> views);

    void popBackStack();

    void removeFragment(Fragment fragment);

    int getSizeFragmentManager();

    void clearAllFragments();

    BaseFragment getLastFragment();
}
