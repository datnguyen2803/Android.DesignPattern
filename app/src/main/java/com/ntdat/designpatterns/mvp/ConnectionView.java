package com.ntdat.designpatterns.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdat.designpatterns.R;
import com.ntdat.designpatterns.mvp.base.MvpBaseView;

public class ConnectionView extends MvpBaseView {
    private final String TAG = "NTDAT" + getClass().getSimpleName();

    private TextView mText;
    public ConnectionView(Context context, View component) {
        super(context, component);
    }

    @Override
    protected void init() {
        mText = (TextView) mComponentView;
    }

    @Override
    public void deinit() {

    }

    @Override
    public void onRequestChange(Bundle request) {
        if(request == null) return;
        if(request.containsKey(Common.CONNECTION_VIEW_ON_REQUEST)) {
            int requestInt = request.getInt(Common.CONNECTION_VIEW_ON_REQUEST);
            if(requestInt == Common.CONNECTION_VIEW_ON_REQUEST_NO_DISPLAY) {
                mText.setVisibility(View.GONE);
            } else {
                mText.setVisibility(View.VISIBLE);
            }
            switch (requestInt) {
                case Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_DISCONNECT_ALL:
                    mText.setText("USB [ ] - BT [ ]");
                    break;
                case Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_ON_BT:
                    mText.setText("USB [ ] - BT [x]");
                    break;
                case Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECTED_USB:
                    mText.setText("USB [x] - BT [ ]");
                    break;
                case Common.CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECT_ALL:
                    mText.setText("USB [x] - BT [x]");
                    break;
            }
        }
    }
}
