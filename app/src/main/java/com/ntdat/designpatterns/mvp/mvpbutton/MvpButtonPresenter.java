//package com.ntdat.designpatterns.mvp.mvpbutton;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.ntdat.designpatterns.mvp.Contract;
//
//public class MvpButtonPresenter implements Contract.Presenter {
//    private static final String TAG = "NTDAT" + MvpButtonView.class.getSimpleName();
//
//    private Context mContext;
//    private MvpButtonView mMvpView;
//    private MvpButtonModel mMvpModel;
//
//    public MvpButtonPresenter(Context context, MvpButtonView mvpButtonView) {
//        mContext = context;
//        mMvpView = mvpButtonView;
//        mMvpModel = new MvpButtonModel(mContext, this);
//    }
//
//
//    // handler for View changes
//    public void onViewChanged() {
//        Log.d(TAG, "Presenter onViewChanged");
//        mMvpModel.update();
//    }
//
//    // handler for Model changes
//    public void onModelChanged(boolean state) {
//        Log.d(TAG, "Presenter onModelChanged");
//        mMvpView.update(state);
//    }
//
//    public void deInit() {
//        mMvpModel.deInit();
//    }
//
//}
