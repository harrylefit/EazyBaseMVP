package vn.eazy.base.mvp.intergration.handler.error.listener;

import android.content.Context;

/**
 * Created by harryle on 6/23/17.
 */

public interface ResponseErrorListener {
    void handleResponseError(Context context, Throwable t);

    ResponseErrorListener EMPTY = new ResponseErrorListener() {
        @Override
        public void handleResponseError(Context context, Throwable t) {

        }
    };
}
