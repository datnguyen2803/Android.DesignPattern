//package com.ntdat.designpatterns.mvp.mvpbutton;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Button;
//
//import com.ntdat.designpatterns.mvp.Contract;
//
//public class MvpButtonView implements Contract.View{
//    private static final String TAG = "NTDAT" + MvpButtonView.class.getSimpleName();
//    private Context mContext = null;
//    private Button mButton = null;
//    private MvpButtonPresenter mMvpPresenter;
//
//    public MvpButtonView(Context context, Button button) {
//        mContext = context;
//        mButton = button;
//
//        mMvpPresenter = new MvpButtonPresenter(mContext, this);
//    }
//
//    @Override
//    // notify UI changed
//    public void notifyChanged(){
//        Log.d(TAG, "View notifyChanged");
//        mMvpPresenter.onViewChanged();
//    }
//
//    @Override
//    // update UI
//    public void update(boolean state) {
//        Log.d(TAG, "View update");
//        if(state)
//            mButton.setText("BT ON");
//        else
//            mButton.setText("BT OFF");
//    }
//
//    public void deInit() {
//        mMvpPresenter.deInit();
//    }
//}
