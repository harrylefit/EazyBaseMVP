package vn.eazy.base.mvp.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Harry on 2/4/17.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int mItemOffset;

    public SpacesItemDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public SpacesItemDecoration(@NonNull Context context,int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}
