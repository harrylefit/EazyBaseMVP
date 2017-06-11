package vn.eazy.base.mvp.helper.snackbar;

import android.view.View;

/**
 * Created by cuong on 2/21/17.
 */

public interface SnackbarInterface {
    /**
     * @param viewParent content view
     * @param message    message will be show
     */
    void show(View viewParent, String message);


    /**
     * @param viewParent
     * @param message
     * @param actionText text for action button
     */
    void show(View viewParent, String message, String actionText);

    /**
     * @param viewParent
     * @param message
     * @param actionIcon action button icon
     */
    void show(View viewParent, String message, int actionIcon);

    /**
     * @param viewParent
     * @param message
     * @param actionIcon
     * @param snackbarColor
     */
    void show(View viewParent, String message, int actionIcon, SnackbarColor snackbarColor);

    /**
     * @param viewParent
     * @param message
     * @param snackbarColor
     */
    void show(View viewParent, String message, SnackbarColor snackbarColor);

    /**
     * @param viewParent
     * @param message
     * @param actionText
     * @param snackbarColor
     */
    void show(View viewParent, String message, String actionText, SnackbarColor snackbarColor);

    /**
     * @param viewParent
     * @param message
     * @param actionListener
     * @param snackbarColor
     * @param snackbarIcon
     */
    void show(View viewParent, String message, String actionText, SnackbarColor snackbarColor, SnackbarIcon snackbarIcon, ClickActionListener actionListener);
}
