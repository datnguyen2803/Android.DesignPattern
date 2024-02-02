package com.ntdat.designpatterns.mvp;

import android.content.Context;
import android.os.Bundle;

import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;
import com.ntdat.designpatterns.mvp.base.MvpBaseView;

public class ConnectionPresenter extends MvpBasePresenter {
    private final String TAG = "NTDAT" + getClass().getSimpleName();
    private int mUsbState = Common.USB_STATE_NO_DEVICE;
    private int mBtState = Common.BT_STATE_OFF;

    protected ConnectionPresenter(Context context, MvpBaseView view) {
        super(context, view);
    }

    @Override
    public void onDataChanged(Bundle data) {
        if(data == null) return;
        if(data.containsKey(Common.USB_MODEL_USB_STATE_KEY)) {
            mUsbState = data.getInt(Common.USB_MODEL_USB_STATE_KEY);
        }
        if(data.containsKey(Common.BT_MODEL_BT_STATE_KEY)) {
            mBtState = data.getInt(Common.BT_MODEL_BT_STATE_KEY);
        }
        Bundle request = new Bundle();
        int requestInt = 0;
        if(mUsbState == Common.USB_STATE_NO_DEVICE) {
            if(mBtState == Common.BT_STATE_OFF)
                requestInt = Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_DISCONNECT_ALL;
            else if(mBtState == Common.BT_STATE_ON)
                requestInt = Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_ON_BT;
        } else if(mUsbState == Common.USB_STATE_DEVICE_CONNECTED) {
            if(mBtState == Common.BT_STATE_OFF)
                requestInt = Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECTED_USB;
            else if(mBtState == Common.BT_STATE_ON)
                requestInt = Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECT_ALL;
        }
        request.putInt(Common.CONNECTION_VIEW_ON_REQUEST, requestInt);
        requestToView(request);
    }

    @Override
    public void onUIChanged(Bundle data) {

    }
}
