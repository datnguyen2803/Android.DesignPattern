package com.ntdat.designpatterns.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class MvpBaseView {
    public Context mContext;
    public View mComponentView;
    private MvpBasePresenter mPresenter;

    public MvpBaseView(Context context, View componentView) {
        mContext = context;
        mComponentView = componentView;
        init();
    }

    /**
     * bind the corresponding presenter
     * @param presenter
     */
    public void bindController(MvpBasePresenter presenter) {
        if(presenter != null)
            mPresenter = presenter;
    }

    /**
     * unbind the corresponding controller
     */
    public void unbindController() {
        mPresenter = null;
    }

    /**
     * notify the controller that the view is changed
     * @param info
     */
    public void notifyUIChanged(Bundle info) {
        if(mPresenter == null) return;
        mPresenter.onUIChanged(info);
    }

    // abstract  methods need implementation
    // find the component in layout: ImageView, Button... and set touch listener
    // be called inside constructor
    protected abstract void init();
    // should be call in destructor
    public abstract void deinit();
    // handle request from controller
    public abstract void onRequestChange(Bundle request);
}
