package vn.eazy.base.mvp.helper.snackbar;

import android.content.Context;
import android.util.TypedValue;

import vn.eazy.base.mvp.R;


/**
 * Created by cuong on 2/21/17.
 */

public class SnackbarColor {
    private int messageColor;
    private int actionColor;
    private int backgroundColor;

    public SnackbarColor(Context context) {
        TypedValue primaryColorValue = new TypedValue();
        TypedValue accentColorValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, primaryColorValue, true);
        context.getTheme().resolveAttribute(R.attr.colorAccent, accentColorValue, true);
        int primaryColor = primaryColorValue.data;
        int accentColor = accentColorValue.data;
        messageColor = accentColor;
        actionColor = accentColor;
        backgroundColor = primaryColor;
    }

    public int getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(int messageColor) {
        this.messageColor = messageColor;
    }

    public int getActionColor() {
        return actionColor;
    }

    public void setActionColor(int actionColor) {
        this.actionColor = actionColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
