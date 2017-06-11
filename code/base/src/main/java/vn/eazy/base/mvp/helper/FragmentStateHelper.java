package vn.eazy.base.mvp.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import vn.eazy.base.mvp.R;
import vn.eazy.base.mvp.base.fragment.BaseFragment;

/**
 * Created by Liam Vo on 3/3/17.
 */

public class FragmentStateHelper implements OnFragmentStateAction {
    private FragmentManager fragmentManager;
    private int idContent = R.id.fragment_content;
    private int stackSelected = -1;
    private int tagCount;
    private List<Stack<BaseFragment>> stacksFragment;
    private BaseFragment[] rootFragments;
    private List<String> fragmentsKeepAlive;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public List<Stack<BaseFragment>> getStacksFragment() {
        return stacksFragment;
    }

    public BaseFragment[] getRootFragments() {
        return rootFragments;
    }

    public List<String> getFragmentsKeepAlive() {
        return fragmentsKeepAlive;
    }

    public FragmentStateHelper(FragmentManager fragmentManager, int idContent) {
        this.fragmentManager = fragmentManager;
        this.idContent = idContent;
        stacksFragment = new ArrayList<>();
        fragmentsKeepAlive = new ArrayList<>();
    }

    public int getStackSelected() {
        return stackSelected;
    }

    @Override
    public void setStacksRootFragment(BaseFragment... fragments) {
        rootFragments = new BaseFragment[fragments.length];
        for (int index = 0; index < fragments.length; index++) {
            stacksFragment.add(new Stack<BaseFragment>());
        }
        rootFragments = fragments;
    }

    @Override
    public void changeRootFragment(BaseFragment fragments, int stackId) {
        clearStack(stackId);
        if (stacksFragment.get(stackId).size() > 0) {
            beginTrans().remove(getFragByTag(stacksFragment.get(stackId).pop().getTag())).commitAllowingStateLoss();
        }
        rootFragments[stackId] = fragments;
        refreshStack(stackId);
    }


    @Override
    public boolean isRootFragment() {
        if (stacksFragment.get(stackSelected).size() <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public void pushFragment(BaseFragment fragment) {
        beginTrans().add(idContent, fragment, generateTag(fragment)).commitAllowingStateLoss();
        detachCurrentFrag();
        stacksFragment.get(stackSelected).push(fragment);
    }

    @Override
    public void pushFragmentKeepOld(BaseFragment fragment) {
        beginTrans().add(idContent, fragment, generateTag(fragment)).commitAllowingStateLoss();
        stacksFragment.get(stackSelected).push(fragment);
        fragmentsKeepAlive.add(stacksFragment.get(stackSelected).peek().getTag());
    }

    @Override
    public void popFragment(int numberPop) {
        if (numberPop >= stacksFragment.get(stackSelected).size()) {
            throw new StringIndexOutOfBoundsException("Number pop out of stack size");
        }
        for (int index = 0; index < numberPop; index++) {
            beginTrans().remove(getFragByTag(stacksFragment.get(stackSelected).pop().getTag())).
                    commitAllowingStateLoss();
        }
        if (!fragmentsKeepAlive.contains(stacksFragment.get(stackSelected).peek().getTag())) {
            beginTrans().attach(getFragByTag(stacksFragment.get(stackSelected).peek().getTag())).
                    commitAllowingStateLoss();
        }
    }

    @Override
    public void showStack(int stackId) {
        if (stackId != stackSelected) {
            attackStack(stackId);
            detachPrevStack();
            stackSelected = stackId;
        }
    }

    @Override
    public void refreshStack(int stackId) {
        if (stackId == stackSelected) {
            attackStack(stackId);
        }
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        beginTrans().replace(idContent, fragment, generateTag(fragment)).commitAllowingStateLoss();
        stacksFragment.get(stackSelected).pop();
        stacksFragment.get(stackSelected).push(fragment);
    }

    @Override
    public void clearStack(int stackId) {
        Stack<BaseFragment> stackFragment = stacksFragment.get(stackId);
        while (stackFragment.size() > 1) {
            beginTrans().remove(getFragByTag(stackFragment.pop().getTag())).commitAllowingStateLoss();
        }
        refreshStack(stackId);
    }


    @Override
    public void clearAllStacks() {
        for (Stack<BaseFragment> stack : stacksFragment) {
            clearStack(stacksFragment.indexOf(stack));
        }
    }

    private String generateTag(BaseFragment fragment) {
        return fragment.getClass().getName() + ++tagCount;
    }

    private Fragment getFragByTag(String tab) {
        return fragmentManager.findFragmentByTag(tab);
    }

    private FragmentTransaction beginTrans() {
        return fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
    }

    private void attackStack(int stackId) {
        if (stacksFragment.get(stackId).size() == 0) {
            initStack(stackId);
        } else {
            beginTrans().attach(getFragByTag(stacksFragment.get(stackId).peek().getTag())).
                    commitAllowingStateLoss();
        }
    }

    private void detachPrevStack() {
        if (stackSelected != -1) {
            detachCurrentFrag();
        }
    }

    private void detachCurrentFrag() {
        beginTrans().detach(getFragByTag(stacksFragment.get(stackSelected).peek().getTag())).commitAllowingStateLoss();
    }

    private void initStack(int stackId) {
        stacksFragment.get(stackId).push(rootFragments[stackId]);
        beginTrans().add(
                idContent,
                stacksFragment.get(stackId).peek(),
                generateTag(stacksFragment.get(stackId).peek())).
                commitAllowingStateLoss();
    }
}
