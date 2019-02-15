package com.changhao.weidu_shopping_demo.presenter;

import com.changhao.weidu_shopping_demo.bean.LoginBean;
import com.changhao.weidu_shopping_demo.bean.RegisterBean;
import com.changhao.weidu_shopping_demo.contract.LoginContract;
import com.changhao.weidu_shopping_demo.model.MyModel;
import com.changhao.weidu_shopping_demo.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginPresenter extends LoginContract.LoginPresenter {

    private MyModel myModel;

    private LoginContract.ILoginView iLoginView;

    public LoginPresenter(LoginContract.ILoginView iLoginView) {
        myModel = new MyModel();
        this.iLoginView = iLoginView;
    }

    @Override
    public void getLogin(HashMap<String, String> params) {
        myModel.getLogin(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                String status = loginBean.getStatus();
                if (iLoginView != null) {
                    iLoginView.onLoginSuccess(status);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iLoginView != null) {
                    iLoginView.onFailed(msg);
                }
            }
        });
    }

    @Override
    public void getRegister(HashMap<String, String> params) {
        myModel.getRegister(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                RegisterBean registerBean = new Gson().fromJson(result, RegisterBean.class);
                String status = registerBean.getStatus();
                if (iLoginView != null) {
                    iLoginView.onRegisterSuccess(status);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iLoginView != null) {
                    iLoginView.onFailed(msg);
                }
            }
        });
    }
}
