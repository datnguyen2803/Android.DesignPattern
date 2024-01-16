package com.ntdat.designpatterns.mvp.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ntdat.designpatterns.mvp.Contract;

public abstract class MvpBaseView implements Contract.View{
    public Context mContext = null;
    private View mViewComponent = null;
    private MvpBasePresenter mPresenter;

    public MvpBaseView(Context context, View component, MvpBasePresenter mvpBasePresenter) {
        mContext = context;
        mViewComponent = component;
        mPresenter = mvpBasePresenter;
    }

    @Override
    // notify UI changed
    public void notifyChanged(String key, int value){
        if(mPresenter != null) {
            mPresenter.onViewChanged(key, value);
        }
    }

    @Override
    // update UI
    public void update(int state) {
        updateUI(state);
    }

    public abstract void updateUI(int state);
    public void init(){}
    public void deInit(){}

}
