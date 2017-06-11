package vn.eazy.base.mvp.state_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import vn.eazy.base.mvp.R;


/**
 * Created by Harry on 4/16/17.
 */

public class MultiStateView extends FrameLayout {
    private static final String TAG_EMPTY = "empty";
    private static final String TAG_LOADING = "loading";
    private static final String TAG_ERROR = "error";

    public static final int VIEW_STATE_UNKNOWN = -1;

    public static final int VIEW_STATE_CONTENT = 0;

    public static final int VIEW_STATE_ERROR = 1;

    public static final int VIEW_STATE_EMPTY = 2;

    public static final int VIEW_STATE_LOADING = 3;

    private int mLoadingViewResId;
    private int mEmptyViewResId;
    private int mErrorViewResId;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VIEW_STATE_UNKNOWN, VIEW_STATE_CONTENT, VIEW_STATE_ERROR, VIEW_STATE_EMPTY, VIEW_STATE_LOADING})
    public @interface ViewState {
    }

    private LayoutInflater mInflater;

    private View mContentView;

    private View mLoadingView;

    private View mErrorView;

    private View mEmptyView;

    private boolean mAnimateViewChanges = false;

    @Nullable
    private StateListener mListener;

    @ViewState
    private int mViewState = VIEW_STATE_UNKNOWN;


    public MultiStateView(@NonNull Context context) {
        super(context);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mInflater = LayoutInflater.from(getContext());
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiStateView);

        mLoadingViewResId = a.getResourceId(R.styleable.MultiStateView_msv_loadingView, -1);

        mEmptyViewResId = a.getResourceId(R.styleable.MultiStateView_msv_emptyView, -1);

        mErrorViewResId = a.getResourceId(R.styleable.MultiStateView_msv_errorView, -1);

        int viewState = a.getInt(R.styleable.MultiStateView_msv_viewState, VIEW_STATE_CONTENT);
        mAnimateViewChanges = a.getBoolean(R.styleable.MultiStateView_msv_animateViewChanges, false);

        switch (viewState) {
            case VIEW_STATE_CONTENT:
                mViewState = VIEW_STATE_CONTENT;
                break;

            case VIEW_STATE_ERROR:
                mViewState = VIEW_STATE_ERROR;
                break;

            case VIEW_STATE_EMPTY:
                mViewState = VIEW_STATE_EMPTY;
                break;

            case VIEW_STATE_LOADING:
                mViewState = VIEW_STATE_LOADING;
                break;

            case VIEW_STATE_UNKNOWN:
            default:
                mViewState = VIEW_STATE_UNKNOWN;
                break;
        }

        a.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mContentView == null) throw new IllegalArgumentException("Content view is not defined");
        setView(VIEW_STATE_UNKNOWN);
    }

    private void checkAndReturnView(View child) {
        if (isValidContentView(child)) mContentView = child;
    }

    @Override
    public void addView(View child) {
        checkAndReturnView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        checkAndReturnView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkAndReturnView(child);
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkAndReturnView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        checkAndReturnView(child);
        super.addView(child, width, height);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkAndReturnView(child);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkAndReturnView(child);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    @Nullable
    public View getView(@ViewState int state) {
        switch (state) {
            case VIEW_STATE_LOADING:
                ensureLoadingView();
                return mLoadingView;

            case VIEW_STATE_CONTENT:
                return mContentView;

            case VIEW_STATE_EMPTY:
                ensureEmptyView();
                return mEmptyView;
            case VIEW_STATE_ERROR:
                ensureErrorView();
                return mErrorView;
            case VIEW_STATE_UNKNOWN:
            default:
                return null;
        }
    }

    @ViewState
    public int getViewState() {
        return mViewState;
    }

    public void setViewState(@ViewState int state) {
        if (state != mViewState) {
            int previous = mViewState;
            mViewState = state;
            setView(previous);
            if (mListener != null) mListener.onStateChanged(mViewState);
        }
    }

    private void ensureStateLoading(int previousState) {
        ensureLoadingView();

        if (mLoadingView == null) {
            throw new NullPointerException("Loading View");
        }

        if (mContentView != null) mContentView.setVisibility(View.GONE);
        if (mErrorView != null) mErrorView.setVisibility(View.GONE);
        if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);

        if (mAnimateViewChanges) {
            animateLayoutChange(getView(previousState));
        } else {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    private void ensureStateEmpty(int previousState) {
        ensureEmptyView();

        if (mEmptyView == null) {
            throw new NullPointerException("Empty View");
        }

        if (mLoadingView != null) mLoadingView.setVisibility(View.GONE);
        if (mErrorView != null) mErrorView.setVisibility(View.GONE);
        if (mContentView != null) mContentView.setVisibility(View.GONE);

        if (mAnimateViewChanges) {
            animateLayoutChange(getView(previousState));
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    private void ensureStateError(int previousState) {
        ensureErrorView();

        if (mErrorView == null) {
            throw new NullPointerException("Error View");
        }

        if (mLoadingView != null) mLoadingView.setVisibility(View.GONE);
        if (mContentView != null) mContentView.setVisibility(View.GONE);
        if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);

        if (mAnimateViewChanges) {
            animateLayoutChange(getView(previousState));
        } else {
            mErrorView.setVisibility(View.VISIBLE);
        }
    }

    private void ensureStateContent(int previousState) {
        if (mContentView == null) {
            // Should never happen, the view should throw an exception if no content view is present upon creation
            throw new NullPointerException("Content View");
        }

        if (mLoadingView != null) mLoadingView.setVisibility(View.GONE);
        if (mErrorView != null) mErrorView.setVisibility(View.GONE);
        if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);

        if (mAnimateViewChanges) {
            animateLayoutChange(getView(previousState));
        } else {
            mContentView.setVisibility(View.VISIBLE);
        }
    }

    private void setView(@ViewState int previousState) {
        switch (mViewState) {
            case VIEW_STATE_LOADING:
                ensureStateLoading(previousState);
                break;
            case VIEW_STATE_EMPTY:
                ensureStateEmpty(previousState);
                break;

            case VIEW_STATE_ERROR:
                ensureStateError(previousState);
                break;

            case VIEW_STATE_CONTENT:
            default:
                ensureStateContent(previousState);
                break;
        }
    }

    private boolean isValidContentView(View view) {
        if (mContentView != null && mContentView != view) {
            return false;
        }
        Object tag = view.getTag(R.id.tag_multistateview);
        if (tag == null) {
            return true;
        }
        if (tag instanceof String) {
            String viewTag = (String) tag;
            if (TextUtils.equals(viewTag, TAG_EMPTY)
                    || TextUtils.equals(viewTag, TAG_ERROR)
                    || TextUtils.equals(viewTag, TAG_LOADING)
                    ) {
                return false;
            }
        }
        return true;
    }

    public void setViewForState(View view, @ViewState int state, boolean switchToState) {
        switch (state) {
            case VIEW_STATE_LOADING:
                if (mLoadingView != null) removeView(mLoadingView);
                mLoadingView = view;
                mLoadingView.setTag(R.id.tag_multistateview, TAG_LOADING);
                addView(mLoadingView);
                break;

            case VIEW_STATE_EMPTY:
                if (mEmptyView != null) removeView(mEmptyView);
                mEmptyView = view;
                mEmptyView.setTag(R.id.tag_multistateview, TAG_EMPTY);
                addView(mEmptyView);
                break;

            case VIEW_STATE_ERROR:
                if (mErrorView != null) removeView(mErrorView);
                mErrorView = view;
                mErrorView.setTag(R.id.tag_multistateview, TAG_ERROR);
                addView(mErrorView);
                break;

            case VIEW_STATE_CONTENT:
                if (mContentView != null) removeView(mContentView);
                mContentView = view;
                addView(mContentView);
                break;
        }

        setView(VIEW_STATE_UNKNOWN);
        if (switchToState) setViewState(state);
    }

    public void setViewForState(View view, @ViewState int state) {
        setViewForState(view, state, false);
    }

    public void setViewForState(@LayoutRes int layoutRes, @ViewState int state, boolean switchToState) {
        if (mInflater == null) mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(layoutRes, this, false);
        setViewForState(view, state, switchToState);
    }

    public void setViewForState(@LayoutRes int layoutRes, @ViewState int state) {
        setViewForState(layoutRes, state, false);
    }

    public void setStateListener(StateListener listener) {
        mListener = listener;
    }

    private void animateLayoutChange(@Nullable final View previousView) {
        if (previousView == null) {
            getView(mViewState).setVisibility(View.VISIBLE);
            return;
        }

        previousView.setVisibility(View.VISIBLE);
        ObjectAnimator anim = ObjectAnimator.ofFloat(previousView, "alpha", 1.0f, 0.0f).setDuration(250L);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                previousView.setVisibility(View.GONE);
                getView(mViewState).setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(getView(mViewState), "alpha", 0.0f, 1.0f).setDuration(250L).start();
            }
        });
        anim.start();
    }

    private void ensureLoadingView() {
        if (mLoadingView == null && mLoadingViewResId > -1) {
            mLoadingView = mInflater.inflate(mLoadingViewResId, this, false);
            mLoadingView.setTag(R.id.tag_multistateview, TAG_LOADING);
            addView(mLoadingView, mLoadingView.getLayoutParams());
            if (mListener != null) mListener.onStateInflated(VIEW_STATE_LOADING, mLoadingView);

            if (mViewState != VIEW_STATE_LOADING) {
                mLoadingView.setVisibility(GONE);
            }
        }
    }

    private void ensureEmptyView() {
        if (mEmptyView == null && mEmptyViewResId > -1) {
            mEmptyView = mInflater.inflate(mEmptyViewResId, this, false);
            mEmptyView.setTag(R.id.tag_multistateview, TAG_EMPTY);
            addView(mEmptyView, mEmptyView.getLayoutParams());
            if (mListener != null) mListener.onStateInflated(VIEW_STATE_EMPTY, mEmptyView);

            if (mViewState != VIEW_STATE_EMPTY) {
                mEmptyView.setVisibility(GONE);
            }
        }
    }

    private void ensureErrorView() {
        if (mErrorView == null && mErrorViewResId > -1) {
            mErrorView = mInflater.inflate(mErrorViewResId, this, false);
            mErrorView.setTag(R.id.tag_multistateview, TAG_ERROR);
            addView(mErrorView, mErrorView.getLayoutParams());
            if (mListener != null) mListener.onStateInflated(VIEW_STATE_ERROR, mErrorView);

            if (mViewState != VIEW_STATE_ERROR) {
                mErrorView.setVisibility(GONE);
            }
        }
    }

    public interface StateListener {
        /**
         * Callback for when the {@link ViewState} has changed
         *
         * @param viewState The {@link ViewState} that was switched to
         */
        void onStateChanged(@ViewState int viewState);

        /**
         * Callback for when a {@link ViewState} has been inflated
         *
         * @param viewState The {@link ViewState} that was inflated
         * @param view      The {@link View} that was inflated
         */
        void onStateInflated(@ViewState int viewState, @NonNull View view);
    }

}
