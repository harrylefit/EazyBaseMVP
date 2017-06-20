package vn.eazy.base.mvp.architect;

import android.content.Intent;

/**
 * Created by harryle on 6/10/17.
 */

public interface IView {
    void showLoading();

    void hideLoading();

    void showMessage(String message);
}
