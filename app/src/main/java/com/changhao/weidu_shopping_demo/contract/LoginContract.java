package com.changhao.weidu_shopping_demo.contract;

import com.changhao.weidu_shopping_demo.net.RequestCallback;

import java.util.HashMap;

public interface LoginContract {
    abstract class LoginPresenter {
        public abstract void getLogin(HashMap<String, String> params);

        public abstract void getRegister(HashMap<String, String> params);
    }

    interface ILoginModel {
        void getLogin(HashMap<String, String> params, RequestCallback requestCallback);

        void getRegister(HashMap<String, String> params, RequestCallback requestCallback);
    }

    interface ILoginView {
        void onLoginSuccess(String result);

        void onRegisterSuccess(String result);

        void onFailed(String msg);
    }
}
