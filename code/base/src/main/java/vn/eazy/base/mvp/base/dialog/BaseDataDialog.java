package vn.eazy.base.mvp.base.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.eazy.base.mvp.R;
import vn.eazy.base.mvp.architect.IModel;
import vn.eazy.base.mvp.helper.CommonHelper;


/**
 * Created by Harry on 2/16/17.
 */

public abstract class BaseDataDialog<T extends IModel> extends BaseAnimationDialog {
    private RecyclerView rvData;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager llm;
    private int widthDialog = -1;
    private int heightDialog = -1;

    public BaseDataDialog(Context context) {
        super(context);
    }

    public BaseDataDialog(Context context, int widthDialog, int heightDialog) {
        super(context);
        this.widthDialog = widthDialog;
        this.heightDialog = heightDialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_data_dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = initAdapter();
        llm = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(llm);
        rvData.setAdapter(adapter);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        bindViews(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bindViews(view);
    }

    private void bindViews(View rootView) {
        rvData = (RecyclerView) rootView.findViewById(R.id.rvData);
        rvData.getLayoutParams().width = widthDialog == -1 ? (int) (CommonHelper.getScreenWidth() * 0.6) : widthDialog;
        rvData.getLayoutParams().height = heightDialog == -1 ? (int) (CommonHelper.getScreenHeight() * 0.7) : heightDialog;
    }

    public void addData(List<T> list) {
//        adapter.addAll(list);
    }

    public void deleteAll() {
        if (rvData != null) {
//            adapter.clear(rvData);
        }
    }

    public void deleteAndAddAll(List<T> list) {
        deleteAll();

    }

    public abstract RecyclerView.Adapter initAdapter();
}
