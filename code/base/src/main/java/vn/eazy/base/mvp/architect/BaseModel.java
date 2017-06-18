package vn.eazy.base.mvp.architect;

import vn.eazy.base.mvp.intergration.IRepositoryManager;

/**
 * Created by harryle on 6/10/17.
 */

public class BaseModel implements IModel {
    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
