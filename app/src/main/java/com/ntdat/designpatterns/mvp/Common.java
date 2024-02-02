package com.ntdat.designpatterns.mvp;

public class Common {

    // BluetoothModel
    public static final String BT_MODEL_BT_STATE_KEY = "BT_MODEL_BT_STATE_KEY";
    public static final int BT_STATE_OFF = 0;
    public static final int BT_STATE_ON = 1;
    public static final String BT_MODEL_ON_REQUEST = "BT_MODEL_ON_REQUEST";
    public static final int BT_MODEL_ON_REQUEST_TURN_OFF = 0;
    public static final int BT_MODEL_ON_REQUEST_TURN_ON = 1;

    // UsbModel
    public static final String USB_MODEL_USB_STATE_KEY = "USB_MODEL_USB_STATE_KEY";
    public static final int USB_STATE_NO_DEVICE = 0;
    public static final int USB_STATE_DEVICE_CONNECTED = 1;

    // ConnectionView
    public static final String CONNECTION_VIEW_ON_REQUEST = "CONNECTION_VIEW_ON_REQUEST";
    public static final int CONNECTION_VIEW_ON_REQUEST_NO_DISPLAY = 0;
    public static final int CONNECTION_VIEW_ON_REQUEST_DISPLAY_DISCONNECT_ALL = 1;
    public static final int CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECTED_USB = 2;
    public static final int CONNECTION_VIEW_ON_REQUEST_DISPLAY_ON_BT = 3;
    public static final int CONNECTION_VIEW_ON_REQUEST_DISPLAY_CONNECT_ALL = 4;


}
