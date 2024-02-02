package com.ntdat.designpatterns.mvp.base;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public abstract class MvpBaseModel {
    public Context mContext;
    protected MvpBaseNotifier mNotifier;

    protected MvpBaseModel(Context context) {
        mContext = context;
        mNotifier = new MvpBaseNotifier();
        init();
        refreshData();
    }

    /**
     * notify to a specific presenter that the data have changed
     * only use to notify to a new connected presenter
     * @param data
     */
    protected void notifyDataChanged(MvpBasePresenter presenter, Bundle data) {
        mNotifier.notifyDataChanged(presenter, data);
    }

    /**
     * notify to all connected controllers that the data have changed
     * @param data
     */
    protected void notifyDataChanged(Bundle data) {
        mNotifier.notifyDataChanged(data);
    }

    /**
     * register/unregister a presenter to listen to the data change
     * only used when a presenter trying to connect to the model
     * @param presenter
     */
    public void registerListener(MvpBasePresenter presenter) {
        if(presenter == null) return;
        mNotifier.addListener(presenter);
        refreshData();
        notifyToNewListener(presenter);
    }
    public void unregisterListener(MvpBasePresenter presenter) {
        if(presenter == null) return;
        mNotifier.removeListener(presenter);
    }

    // abstract  methods need implementation
    // register receiver, resolver...
    // be called inside constructor
    protected abstract void init();
    // unregister
    public abstract void deinit();
    // handle request from controllers
    public abstract void onRequestChange(Bundle request);
    // notify current state to new listener (controller)
    protected abstract void notifyToNewListener(MvpBasePresenter controller);
    // refresh to get current data
    protected abstract void refreshData();


    protected static class MvpBaseNotifier {
        protected List<MvpBasePresenter> mPresenters = new ArrayList<>();

        public void addListener(MvpBasePresenter presenter) {
            mPresenters.add(presenter);
        }

        public void removeListener(MvpBasePresenter presenter) {
            mPresenters.remove(presenter);
        }

        public void notifyDataChanged(Bundle data) {
            for (MvpBasePresenter presenter : mPresenters) {
                presenter.onDataChanged(data);
            }
        }

        public void notifyDataChanged(MvpBasePresenter presenter, Bundle data) {
            presenter.onDataChanged(data);
        }
    }
}
