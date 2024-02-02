package com.ntdat.designpatterns.mvp;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.ntdat.designpatterns.mvp.base.MvpBaseModel;
import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;

public class BluetoothModel extends MvpBaseModel {
    private final String TAG = "NTDAT" + getClass().getSimpleName();

    int mBtState = Common.BT_STATE_OFF;
    private BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mBluetoothReceiver;

    public BluetoothModel(Context context) {
        super(context);
    }


    @Override
    protected void init() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                //Log.d(TAG, "onReceive: " + action);

                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    boolean bIsNeedUpdate = true;
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                    //Log.d(TAG, "\t\t state: " + state);
                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            mBtState = Common.BT_STATE_OFF;
                            break;
                        case BluetoothAdapter.STATE_ON:
                            mBtState = Common.BT_STATE_ON;
                            break;
                        default:
                            bIsNeedUpdate = false;
                            break;
                    }
                    if (bIsNeedUpdate) {
                        Bundle data = new Bundle();
                        data.putInt(Common.BT_MODEL_BT_STATE_KEY, mBtState);
                        notifyDataChanged(data);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(mBluetoothReceiver, filter);

    }

    @Override
    public void deinit() {
        mContext.unregisterReceiver(mBluetoothReceiver);
    }

    @Override
    public void onRequestChange(Bundle request) {
        if(request != null && request.containsKey(Common.BT_MODEL_ON_REQUEST)) {
            int requestInt = request.getInt(Common.BT_MODEL_ON_REQUEST);
            switch (requestInt) {
                case Common.BT_MODEL_ON_REQUEST_TURN_OFF:
                    mBluetoothAdapter.disable();
                    break;
                case Common.BT_MODEL_ON_REQUEST_TURN_ON:
                    mBluetoothAdapter.enable();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void notifyToNewListener(MvpBasePresenter controller) {
        Bundle data = new Bundle();
        data.putInt(Common.BT_MODEL_BT_STATE_KEY, mBtState);
        notifyDataChanged(data);
    }

    @Override
    protected void refreshData() {
        int btState = mBluetoothAdapter.getState();
        switch (btState) {
            case BluetoothAdapter.STATE_OFF:
                mBtState = Common.BT_STATE_OFF;
                break;
            case BluetoothAdapter.STATE_ON:
                mBtState = Common.BT_STATE_ON;
                break;
            default:
                break;
        }
    }
}
