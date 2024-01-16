package com.ntdat.designpatterns.mvp.icon;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.ntdat.designpatterns.mvp.base.MvpBaseModel;
import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;

public class IconBluetoothModel extends MvpBaseModel {
    private static final String TAG = "NTDAT" + IconBluetoothModel.class.getSimpleName();
    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: " + action);

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                Log.d(TAG, "\t\t state: " + state);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        // Bluetooth is turned off
                        notifyChanged(IconBluetoothPresenter.MODEL_KEY_STATE_CHANGE, IconBluetoothPresenter.MODEL_VALUE_STATE_CHANGE_OFF);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        // Bluetooth is turned on
                        notifyChanged(IconBluetoothPresenter.MODEL_KEY_STATE_CHANGE, IconBluetoothPresenter.MODEL_VALUE_STATE_CHANGE_ON);
                        break;
                }
            }
        }
    };
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public IconBluetoothModel(Context context, MvpBasePresenter mvpBasePresenter) {
        super(context, mvpBasePresenter);
    }

    @Override
    public void updateData(int state) {
        switch (state) {
            case IconBluetoothPresenter.MODEL_VALUE_STATE_CHANGE_OFF:
                mBluetoothAdapter.disable();
                break;
            case IconBluetoothPresenter.MODEL_UPDATE_TURN_ON:
                mBluetoothAdapter.enable();
                break;
            default:
                break;
        }
    }

    @Override
    public void init() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(mBluetoothReceiver, filter);
        if(mBluetoothAdapter.isEnabled()) {
            notifyChanged(IconBluetoothPresenter.MODEL_KEY_STATE_CHANGE, IconBluetoothPresenter.MODEL_VALUE_STATE_CHANGE_ON);
        } else {
            notifyChanged(IconBluetoothPresenter.MODEL_KEY_STATE_CHANGE, IconBluetoothPresenter.MODEL_VALUE_STATE_CHANGE_OFF);
        }
    }

    @Override
    public void deInit() {
        mContext.unregisterReceiver(mBluetoothReceiver);
    }
}
