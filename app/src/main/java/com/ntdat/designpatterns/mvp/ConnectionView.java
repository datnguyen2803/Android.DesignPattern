package com.ntdat.designpatterns.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ntdat.designpatterns.mvp.base.MvpBaseView;

public class ConnectionView extends MvpBaseView {
    private final String TAG = "NTDAT" + getClass().getSimpleName();

    public ConnectionView(Context context, View component) {
        super(context, component);
    }

    @Override
    protected void init() {

    }

    @Override
    public void deinit() {

    }

    @Override
    public void onRequestChange(Bundle request) {

    }
}
