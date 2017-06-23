package vn.eazy.base.mvp.intergration.handler.error;

import android.content.Context;

import vn.eazy.base.mvp.intergration.handler.error.listener.ResponseErrorListener;

/**
 * Created by harryle on 6/23/17.
 */

public class ErrorHandlerFactory {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;


    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
        this.mContext = mContext;
    }


    public void handleError(Throwable throwable) {
        mResponseErrorListener.handleResponseError(mContext, throwable);
    }
}
