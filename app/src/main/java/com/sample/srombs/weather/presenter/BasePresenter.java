package com.sample.srombs.weather.presenter;

/**
 * Created by srombs on 4/7/17.
 */

public abstract class BasePresenter<T extends BaseView> {

    public abstract void onAttach(T view);
    public abstract void onDetach();

}
