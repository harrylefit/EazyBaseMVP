package vn.eazy.base.mvp.base;

import android.os.Bundle;

/**
 * Created by Harry on 12/24/16.
 */
public class CallbackObject {
    private int value;
    private String raw;
    private Bundle bundle;

    public int getValue() {
        return value;
    }

    public CallbackObject setValue(int value) {
        this.value = value;
        return this;
    }

    public String getRaw() {
        return raw;
    }

    public CallbackObject setRaw(String raw) {
        this.raw = raw;
        return this;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public CallbackObject setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }
}
