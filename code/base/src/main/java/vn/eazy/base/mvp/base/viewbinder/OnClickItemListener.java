package vn.eazy.base.mvp.base.viewbinder;

import android.view.View;

import vn.eazy.base.mvp.architect.BaseModel;

/**
 * Created by Harry on 12/26/16.
 */

public interface OnClickItemListener<M extends BaseModel> {
    void onClickItem(View view, int pos, M model);
}
