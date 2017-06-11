package vn.eazy.base.mvp.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import vn.eazy.base.mvp.R;
import vn.eazy.base.mvp.base.data.BaseObject;

/**
 * Created by Harry on 12/25/16.
 */

public abstract class BaseWithDataFragment<V extends BaseObject> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private TYPE_LAYOUT_MANAGER type;

    public enum TYPE_LAYOUT_MANAGER {
        LINEAR_VERTICAL, LINEAR_HOR, GRID
    }

    protected RecyclerView rvData;
    protected RecyclerView.Adapter adapter;
    protected SwipeRefreshLayout swipeRefresh;

    @Override
    public int getLayoutId() {
        return R.layout.layout_data_content;
    }

    @Override
    public void preInitLayout() {
        super.preInitLayout();
        rvData = (RecyclerView) rootView.findViewById(R.id.rvData);
        swipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
    }

    private void checkAndSetLayoutManagerByType() {
        switch (getTypeLayoutManager()) {
            case LINEAR_HOR:
                rvData.setLayoutManager(new LinearLayoutManager(getBaseActivity().getApplicationContext()
                        , LinearLayoutManager.HORIZONTAL, false));
                break;
            case LINEAR_VERTICAL:
                rvData.setLayoutManager(new LinearLayoutManager(getBaseActivity().getApplicationContext()));
                break;
            case GRID:
                //todo not yet
                rvData.setLayoutManager(new GridLayoutManager(getBaseActivity().getApplicationContext(), 3));
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkAndSetLayoutManagerByType();
        if (adapter == null) {
            adapter = initAdapter();
        }
        rvData.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(this);
    }


    public void disableSwipeRefreshLayout(){
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
            if(adapter != null) {
//                adapter.clear(rvData);
            }
        }
    }

    @Override
    public void onRefresh() {

    }

    public abstract RecyclerView.Adapter initAdapter();

    public abstract TYPE_LAYOUT_MANAGER getTypeLayoutManager();
}
