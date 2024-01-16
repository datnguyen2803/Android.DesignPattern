package com.ntdat.designpatterns.mvp.base;

import android.content.Context;

import com.ntdat.designpatterns.mvp.Contract;

public abstract class MvpBaseModel implements Contract.Model{
    public Context mContext;
    private MvpBasePresenter mPresenter;

    public MvpBaseModel(Context context, MvpBasePresenter mvpBasePresenter) {
        mContext = context;
        mPresenter = mvpBasePresenter;
    }

    @Override
    public void notifyChanged(String key, int value) {
        mPresenter.onModelChanged(key, value);
    }

    // update data
    public void update(int state) {
        updateData(state);
    }

    public abstract void updateData(int state);
    public void init(){}
    public void deInit(){}
}
