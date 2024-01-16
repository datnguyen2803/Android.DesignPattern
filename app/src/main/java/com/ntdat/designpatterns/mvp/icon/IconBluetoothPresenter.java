package com.ntdat.designpatterns.mvp.icon;

import android.content.Context;

import com.ntdat.designpatterns.mvp.base.MvpBaseModel;
import com.ntdat.designpatterns.mvp.base.MvpBasePresenter;
import com.ntdat.designpatterns.mvp.base.MvpBaseView;

public class IconBluetoothPresenter extends MvpBasePresenter {
    private static final String TAG = "NTDAT" + IconBluetoothPresenter.class.getSimpleName();

    // VIEW constants
    public static final String VIEW_KEY_TOUCH = "touch";
    public static final int VIEW_VALUE_TOUCH_DOWN = 0;
    public static final int VIEW_UPDATE_OFF = 0;
    public static final int VIEW_UPDATE_ON = 1;

    // MODEL constants
    public static final String MODEL_KEY_STATE_CHANGE = "stateChange";
    public static final int MODEL_VALUE_STATE_CHANGE_OFF = 0;
    public static final int MODEL_VALUE_STATE_CHANGE_ON = 1;
    public static final int MODEL_UPDATE_TURN_OFF = 0;
    public static final int MODEL_UPDATE_TURN_ON = 1;

    private boolean mCurrentBtState = false;

    public IconBluetoothPresenter(Context context, MvpBaseView mvpBaseView, MvpBaseModel mvpBaseModel) {
        super(context, mvpBaseView, mvpBaseModel);
        init();
    }

    @Override
    public int handleViewChange(String key, int value) {
        int retValue = MODEL_UPDATE_TURN_OFF;
        switch (key) {
            case VIEW_KEY_TOUCH: {
                if(mCurrentBtState)
                    retValue = MODEL_UPDATE_TURN_OFF;
                else
                    retValue = MODEL_UPDATE_TURN_ON;
                break;
            }
            default:
                break;
        }
        return retValue;
    }

    @Override
    public int handleModelChange(String key, int value) {
        int retValue = VIEW_UPDATE_OFF;
        switch (key) {
            case MODEL_KEY_STATE_CHANGE: {
                if(value == MODEL_VALUE_STATE_CHANGE_OFF){
                    mCurrentBtState = false;
                    retValue = VIEW_UPDATE_OFF;
                } else if(value == MODEL_VALUE_STATE_CHANGE_ON) {
                    mCurrentBtState = true;
                    retValue = VIEW_UPDATE_ON;
                }
                break;
            }
            default:
                break;
        }
        return retValue;
    }
}
