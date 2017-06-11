package vn.eazy.base.mvp.helper;


import vn.eazy.base.mvp.base.fragment.BaseFragment;

/**
 * Created by Harry on 12/24/16.
 */

public interface OnFragmentStateAction {

    void setStacksRootFragment(BaseFragment... fragments);

    void changeRootFragment(BaseFragment fragments, int stackId);

    boolean isRootFragment();

    void pushFragment(BaseFragment fragment);

    void pushFragmentKeepOld(BaseFragment fragment);

    void popFragment(int numberPop);

    void showStack(int stackId);

    void refreshStack(int stackId);

    void replaceFragment(BaseFragment fragment);

    void clearStack(int stackId);

    void clearAllStacks();

}
