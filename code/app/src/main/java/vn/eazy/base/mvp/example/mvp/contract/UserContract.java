package vn.eazy.base.mvp.example.mvp.contract;

import com.tbruyelle.rxpermissions.RxPermissions;

import net.idik.lib.slimadapter.SlimAdapter;

import java.util.List;

import io.reactivex.Flowable;
import vn.eazy.base.mvp.architect.IModel;
import vn.eazy.base.mvp.architect.IView;
import vn.eazy.base.mvp.example.mvp.model.entity.User;

/**
 * Created by harryle on 6/18/17.
 */

public interface UserContract {
    interface View extends IView {
        void setAdapter(SlimAdapter adapter);

        void startLoadMore();

        void endLoadMore();

        RxPermissions getRxPermissions();
    }

    interface Model extends IModel {
        Flowable<List<User>> getUsers();
    }
}
