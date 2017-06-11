package vn.eazy.base.mvp.helper.snackbar;

/**
 * Created by cuong on 2/21/17.
 */

public class SnackbarIcon {
    private int leftIconRes;
    private int rightIconRes;
    private int leftIconSize;
    private int rightIconSize;
    private int iconPadding;

    public SnackbarIcon(){
        iconPadding = 8;
    }

    public int getLeftIconRes() {
        return leftIconRes;
    }

    public void setLeftIconRes(int leftIconRes) {
        this.leftIconRes = leftIconRes;
    }

    public int getRightIconRes() {
        return rightIconRes;
    }

    public void setRightIconRes(int rightIconRes) {
        this.rightIconRes = rightIconRes;
    }

    public int getLeftIconSize() {
        return leftIconSize;
    }

    public void setLeftIconSize(int leftIconSize) {
        this.leftIconSize = leftIconSize;
    }

    public int getRightIconSize() {
        return rightIconSize;
    }

    public void setRightIconSize(int rightIconSize) {
        this.rightIconSize = rightIconSize;
    }

    public int getIconPadding() {
        return iconPadding;
    }

    public void setIconPadding(int iconPadding) {
        this.iconPadding = iconPadding;
    }
}
