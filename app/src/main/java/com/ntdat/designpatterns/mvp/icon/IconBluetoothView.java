package com.ntdat.designpatterns.mvp.icon;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ntdat.designpatterns.R;
import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;
import com.ntdat.designpatterns.mvp.base.MvpBaseView;

public class IconBluetoothView extends MvpBaseView {
    private static final String TAG = "NTDAT" + IconBluetoothView.class.getSimpleName();
    private ImageView mImageView;

    public IconBluetoothView(Context context, View component, MvpBasePresenter mvpBasePresenter) {
        super(context, component, mvpBasePresenter);
        mImageView = (ImageView) component;
    }

    @Override
    public void updateUI(int state) {
        switch (state) {
            case IconBluetoothPresenter.VIEW_UPDATE_OFF:
                mImageView.setImageResource(R.drawable.ic_bt_disable);
                break;
            case IconBluetoothPresenter.VIEW_UPDATE_ON:
                mImageView.setImageResource(R.drawable.ic_bt_enable);
            break;
        }
    }
}
