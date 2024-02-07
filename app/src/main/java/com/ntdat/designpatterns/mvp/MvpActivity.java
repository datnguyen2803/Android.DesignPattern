package com.ntdat.designpatterns.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ntdat.designpatterns.R;

public class MvpActivity extends AppCompatActivity {
    private static final String TAG = "NTDAT" + MvpActivity.class.getSimpleName();
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">>> onCreate");
        setContentView(R.layout.activity_main);

        BluetoothModel mBluetoothModel = new BluetoothModel(this);
        UsbModel mUsbModel = new UsbModel(this);
        ConnectionView mConnectionView = new ConnectionView(this, findViewById(R.id.mvp_text_view));
        ConnectionPresenter mConnectionPresenter = new ConnectionPresenter(this, mConnectionView);

        mConnectionPresenter.connectModel(mBluetoothModel);
        mConnectionPresenter.connectModel(mUsbModel);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}