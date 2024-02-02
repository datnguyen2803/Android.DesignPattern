package com.ntdat.designpatterns.mvp.base;

import android.content.Context;
import android.os.Bundle;

import java.util.HashMap;

public abstract class MvpBasePresenter {

    public MvpBaseView mView;
    public Context mContext;
    private HashMap<String, MvpBaseModel> mModelList;

    protected MvpBasePresenter(Context context, MvpBaseView view) {
        mContext = context;
        mView = view;
        init();
    }

    // already called inside constructor
    private void init() {
        mModelList = new HashMap<String, MvpBaseModel>();
        if(mView != null)
            mView.bindController(this);
    }

    // should be call in destructor
    public void deinit() {
        for(MvpBaseModel model : mModelList.values()) {
            model.unregisterListener(this);
        }
        // for (BaseModel model : mModelList) {
        // 	model.unregisterListener(this);
        // }
        if(mView != null)
            mView.unbindController();
    }

    /**
     * Connect/Disconnect a model
     * @param model
     */
    public void connectModel(MvpBaseModel model) {
        if(model == null) return;
        mModelList.put(model.getClass().getSimpleName(), model);
        model.registerListener(this);
    }

    public void disconnectModel(MvpBaseModel model) {
        if(model == null) return;
        mModelList.remove(model.getClass().getSimpleName());
        model.unregisterListener(this);
    }

    /**
     * Request to view
     * @param request
     */
    public void requestToView(Bundle request) {
        if(mView != null)
            mView.onRequestChange(request);
    }

    /**
     * Request to a specific model
     * @param model - model to from getModel()
     * @param request
     */
    protected void requestToModel(MvpBaseModel model, Bundle request) {
        if(model != null)
            model.onRequestChange(request);
    }


    /**
     * Get model in model list by name
     * @param modelName
     * @return
     */
    public MvpBaseModel getModel(String modelName) {
        return mModelList.get(modelName);
    }

    // abstract  methods need implementation
    // handle change from models
    public abstract void onDataChanged(Bundle data);
    // handle change from view
    public abstract void onUIChanged(Bundle data);

}
