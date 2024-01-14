package com.ntdat.designpatterns.mvp;

public interface Contract {
    interface View {
        // notify UI changed
        void notifyChanged();

        // update UI
        void update(boolean state);
    }

    interface Model {
        // notify data changed
        void notifyChanged(boolean state);

        // update data
        void update();
    }

    interface Presenter {
        // handler for View changes
        void onViewChanged();

        // handler for Model changes
        void onModelChanged(boolean state);
    }
}
