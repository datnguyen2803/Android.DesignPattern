package com.ntdat.designpatterns.mvp;

public interface Contract {
    interface View {
        // notify UI changed
        void notifyChanged(String key, int value);

        // update UI
        void update(int state);
    }

    interface Model {
        // notify data changed
        void notifyChanged(String key, int value);

        // update data
        void update(int state);
    }

    interface Presenter {
        // handler for View changes
        void onViewChanged(String key, int value);

        // handler for Model changes
        void onModelChanged(String key, int value);
    }
}
