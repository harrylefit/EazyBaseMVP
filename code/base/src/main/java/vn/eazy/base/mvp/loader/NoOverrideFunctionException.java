package vn.eazy.base.mvp.loader;

/**
 * Created by Harry on 2/21/17.
 */

public class NoOverrideFunctionException extends Exception {
    private static final String MSG = "This function %s have not override yet!";

    public NoOverrideFunctionException(String nameClass) {
        super(String.format(MSG, nameClass));
    }
}
