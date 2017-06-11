package vn.eazy.base.mvp.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.idik.lib.slimadapter.SlimAdapter;

import vn.eazy.base.mvp.R;
import vn.eazy.base.mvp.recyclerview.AutoFitGridRecyclerView;
import vn.eazy.base.mvp.state_view.MultiStateView;


/**
 * Created by Harry on 12/25/16.
 */

public abstract class BaseMainWithDataFragment extends BaseMainFragment implements SwipeRefreshLayout.OnRefreshListener {
    protected RecyclerView rvData;
    protected SlimAdapter adapter;
    protected SwipeRefreshLayout swipeRefresh;
    private LinearLayout rootLayout;
    private ViewStubCompat replaceLayout;
    private MultiStateView multiStateView;
    private View extraView;

    @Override
    public int getLayoutId() {
        return R.layout.layout_data_content;
    }

    private void checkAndSetLayoutManagerByType() {
        switch (getTypeLayoutManager()) {
            case LINEAR_HOR:
                rvData = createNormalRecyclerView();
                rvData.setLayoutManager(new LinearLayoutManager(getBaseActivity().getApplicationContext()
                        , LinearLayoutManager.HORIZONTAL, false));
                break;
            case LINEAR_VERTICAL:
                rvData = createNormalRecyclerView();
                rvData.setLayoutManager(new LinearLayoutManager(getBaseActivity().getApplicationContext()));
                break;
            case GRID:
                rvData = createGridRecyclerView();
                break;
            default:
                break;
        }
    }

    private LinearLayout.LayoutParams generateLayoutParamsForRecyclerView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.weight = 1;
        return params;
    }

    protected RecyclerView createNormalRecyclerView() {
        RecyclerView normalRecyclerView = new RecyclerView(getContext());
        normalRecyclerView.setLayoutParams(generateLayoutParamsForRecyclerView());
        swipeRefresh.addView(normalRecyclerView);
        return normalRecyclerView;
    }

    protected RecyclerView createGridRecyclerView() {
        RecyclerView gridRecyclerView = new AutoFitGridRecyclerView(getContext(), spanCountForGridLayout());
        gridRecyclerView.setLayoutParams(generateLayoutParamsForRecyclerView());
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerView.setClipToPadding(false);
        swipeRefresh.addView(gridRecyclerView);
        return gridRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo setup recyclerview

        checkAndSetLayoutManagerByType();
        adapter = initAdapter();
        swipeRefresh.setOnRefreshListener(this);
    }

    public void disableSwipeRefreshLayout() {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    public void clearAllData() {
        if (adapter != null) {

        }
    }

    protected void disableSwipeRefresh() {
        enableSwipeRefresh(false);
    }

    protected void enableSwipeRefresh(boolean enable) {
        if (swipeRefresh != null) {
            swipeRefresh.setEnabled(enable);
        }
    }

    public void hideRefreshLayoutAndClearData() {
        disableSwipeRefreshLayout();
        clearAllData();
    }

    @Override
    public void onRefresh() {

    }

    protected int spanCountForGridLayout() {
        return 1;
    }

    @Override
    public void preInitLayout() {
        super.preInitLayout();
        //todo setup ExtraView
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        rootLayout = (LinearLayout) rootView.findViewById(R.id.root_layout);
        replaceLayout = (ViewStubCompat) rootView.findViewById(R.id.replace_layout);
        multiStateView = (MultiStateView) rootView.findViewById(R.id.multiStateView);
        if (inflateExtraView() != -1) {
            replaceLayout.setLayoutResource(inflateExtraView());
            replaceLayout.inflate();
        }
        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(onRefreshListener());

        multiStateView.setStateListener(onStateListener());
        return view;
    }

    protected SwipeRefreshLayout.OnRefreshListener onRefreshListener() {
        return null;
    }

    protected MultiStateView.StateListener onStateListener() {
        return null;
    }

    public LinearLayout getRootLayout() {
        return rootLayout;
    }

    public ViewStubCompat getReplaceLayout() {
        return replaceLayout;
    }

    public View getExtraView() {
        return extraView;
    }

    public abstract SlimAdapter initAdapter();

    public abstract TYPE_LAYOUT_MANAGER getTypeLayoutManager();

    public int inflateExtraView() {
        return -1;
    }

    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    public enum TYPE_LAYOUT_MANAGER {
        LINEAR_VERTICAL, LINEAR_HOR, GRID
    }
}
