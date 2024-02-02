package com.ntdat.designpatterns.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.ntdat.designpatterns.mvp.base.MvpBaseModel;
import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;

import java.util.HashMap;

public class UsbModel extends MvpBaseModel {
    private final String TAG = "NTDAT" + getClass().getSimpleName();
    private int mUsbState = Common.USB_STATE_NO_DEVICE;
    private UsbManager mUsbManager;
    private BroadcastReceiver mUsbReceiver;

    public UsbModel(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        mUsbReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, android.content.Intent intent) {
                String action = intent.getAction();

                boolean bIsNeedUpdate = true;

                if (action.equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)
                || action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                    refreshData();
                } else {
                    bIsNeedUpdate = false;
                }
                if(bIsNeedUpdate) {
                    Bundle data = new Bundle();
                    data.putInt(Common.USB_MODEL_USB_STATE_KEY, mUsbState);
                    notifyDataChanged(data);
                }
            }
        };
        IntentFilter filter = new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        mContext.registerReceiver(mUsbReceiver, filter);
    }

    @Override
    public void deinit() {
        mContext.unregisterReceiver(mUsbReceiver);
    }

    @Override
    public void onRequestChange(Bundle request) {

    }

    @Override
    protected void notifyToNewListener(MvpBasePresenter controller) {
        Bundle data = new Bundle();
        data.putInt(Common.USB_MODEL_USB_STATE_KEY, mUsbState);
        notifyDataChanged(data);
    }

    @Override
    protected void refreshData() {
        if(mUsbManager == null) {
            mUsbState = Common.USB_STATE_NO_DEVICE;
            return;
        }
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        if(deviceList.size() == 0) {
            mUsbState = Common.USB_STATE_NO_DEVICE;
        } else {
            mUsbState = Common.USB_STATE_DEVICE_CONNECTED;
        }
    }
}
