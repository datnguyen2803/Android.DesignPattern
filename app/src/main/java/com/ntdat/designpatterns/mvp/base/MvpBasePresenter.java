package com.ntdat.designpatterns.mvp.base;

import android.content.Context;

import com.ntdat.designpatterns.mvp.Contract;

public abstract class MvpBasePresenter implements Contract.Presenter{
    public Context mContext;
    private MvpBaseView mView;
    private MvpBaseModel mModel;

    public MvpBasePresenter(Context context, MvpBaseView mvpBaseView, MvpBaseModel mvpBaseModel) {
        mContext = context;
        mView = mvpBaseView;
        mModel = mvpBaseModel;
    }


    // handler for View changes
    @Override
    public void onViewChanged(String key, int value) {
        int newState = handleViewChange(key, value);
        if(mModel != null) {
            mModel.update(newState);
        }
    }

    // handler for Model changes
    @Override
    public void onModelChanged(String key, int value) {
        int newState = handleModelChange(key, value);
        if(mView != null) {
            mView.update(newState);
        }
    }

    // return state to update Model
    public abstract int handleViewChange(String key, int value);

    // return state to update View
    public abstract int handleModelChange(String key, int value);

    public void init() {
        if(mView != null) {
            mView.init();
        }
        if(mModel != null) {
            mModel.init();
        }
    }
    public void deInit() {
        if(mView != null) {
            mView.deInit();
        }
        if(mModel != null) {
            mModel.deInit();
        }
    }
}
