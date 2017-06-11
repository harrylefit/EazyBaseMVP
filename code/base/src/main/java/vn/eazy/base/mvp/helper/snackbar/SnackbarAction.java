package vn.eazy.base.mvp.helper.snackbar;

/**
 * Created by cuong on 2/21/17.
 */

public class SnackbarAction {
    private String actionText;
    private SnackbarAction action;

    public SnackbarAction(String actionText, SnackbarAction action) {
        this.actionText = actionText;
        this.action = action;
    }

    public String getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    public SnackbarAction getAction() {
        return action;
    }

    public void setAction(SnackbarAction action) {
        this.action = action;
    }
}
