//package com.ntdat.designpatterns.mvp.mvpbutton;
//
//import android.Manifest;
//import android.bluetooth.BluetoothAdapter;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.util.Log;
//
//import androidx.core.app.ActivityCompat;
//
//import com.ntdat.designpatterns.mvp.Contract;
//
//public class MvpButtonModel implements Contract.Model {
//    private static final String TAG = "NTDAT" + MvpButtonView.class.getSimpleName();
//    private Context mContext;
//    private MvpButtonPresenter mMvpPresenter;
//    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            Log.d(TAG, "onReceive: " + action);
//
//            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
//                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
//                Log.d(TAG, "\t\t state: " + state);
//                switch (state) {
//                    case BluetoothAdapter.STATE_OFF:
//                        // Bluetooth is turned off
//                        notifyChanged(false);
//                        break;
//                    case BluetoothAdapter.STATE_ON:
//                        // Bluetooth is turned on
//                        notifyChanged(true);
//                        break;
//                }
//            }
//        }
//    };
//    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//    private boolean mBluetoothState = mBluetoothAdapter.isEnabled();
//
//    public MvpButtonModel(Context context, MvpButtonPresenter mvpButtonPresenter) {
//        mContext = context;
//        mMvpPresenter = mvpButtonPresenter;
//        init();
//    }
//
//    private void init() {
//        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//        mContext.registerReceiver(mBluetoothReceiver, filter);
//
//    }
//
//    public void deInit() {
//        mContext.unregisterReceiver(mBluetoothReceiver);
//    }
//
//    @Override
//    // notify data changed
//    public void notifyChanged(boolean state) {
//        Log.d(TAG, "Model notifyChanged");
//        mMvpPresenter.onModelChanged(state);
//    }
//
//    @Override
//    // update data
//    public void update() {
//        mBluetoothState = mBluetoothAdapter.isEnabled();
//        Log.d(TAG, "Model update mBluetoothState = " + mBluetoothState);
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            Log.e(TAG, "No permission");
//            return;
//        }
//        if (mBluetoothState) {
//            mBluetoothAdapter.disable();
//            mBluetoothState = false;
//        } else {
//            mBluetoothAdapter.enable();
//            mBluetoothState = true;
//        }
//    }
//}
