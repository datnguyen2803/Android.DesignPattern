package com.ntdat.designpatterns.singleton;

public class BaseSingleton {
    private static BaseSingleton mInstance;

    private BaseSingleton() {
    }

    public static synchronized BaseSingleton getmInstance() {
        if (mInstance == null) {
            mInstance = new BaseSingleton();
        }
        return mInstance;

    }
}
