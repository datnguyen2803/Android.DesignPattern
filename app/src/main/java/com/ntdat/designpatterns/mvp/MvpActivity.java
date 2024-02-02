package com.ntdat.designpatterns.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ntdat.designpatterns.R;
import com.ntdat.designpatterns.mvp.mvpbutton.MvpButtonModel;
import com.ntdat.designpatterns.mvp.mvpbutton.MvpButtonPresenter;
import com.ntdat.designpatterns.mvp.mvpbutton.MvpButtonView;

public class MvpActivity extends AppCompatActivity {
    private static final String TAG = "NTDAT" + MvpActivity.class.getSimpleName();
    private Button mButton;
    private MvpButtonView mMvpButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">>> onCreate");
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.test);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button onClick");
                mMvpButtonView.notifyChanged();

            }
        });
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void init() {
        mMvpButtonView = new MvpButtonView(this, mButton);
    }

    private void deInit() {
        mMvpButtonView.deInit();
    }
}