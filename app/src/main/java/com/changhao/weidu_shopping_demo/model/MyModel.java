package com.changhao.weidu_shopping_demo.model;

import com.changhao.weidu_shopping_demo.api.Api;
import com.changhao.weidu_shopping_demo.contract.HomeContract;
import com.changhao.weidu_shopping_demo.contract.ShoppingContract;
import com.changhao.weidu_shopping_demo.contract.LoginContract;
import com.changhao.weidu_shopping_demo.net.OkHttpCallback;
import com.changhao.weidu_shopping_demo.net.RequestCallback;
import com.changhao.weidu_shopping_demo.utils.OkHttpUtils;

import java.util.HashMap;

public class MyModel implements ShoppingContract.IShoppingMolde, LoginContract.ILoginModel, HomeContract.IHomeMdel {
    @Override
    public void getLogin(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpUtils.getInstance().doPost(Api.API_URL + Api.API_LOGIN, params, new OkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (requestCallback != null) {
                    requestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (requestCallback != null) {
                    requestCallback.onFailed(msg);
                }
            }
        });
    }

    @Override
    public void getRegister(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpUtils.getInstance().doPost(Api.API_URL + Api.API_REGISTER, params, new OkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (requestCallback != null) {
                    requestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (requestCallback != null) {
                    requestCallback.onFailed(msg);
                }
            }
        });
    }

    @Override
    public void getHome(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpUtils.getInstance().doGet(Api.API_URL + Api.API_HOME, params, new OkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (requestCallback != null) {
                    requestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (requestCallback != null) {
                    requestCallback.onFailed(msg);
                }
            }
        });
    }

    @Override
    public void getBanner(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpUtils.getInstance().doGet(Api.API_URL + Api.API_BANNER, params, new OkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (requestCallback != null) {
                    requestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (requestCallback != null) {
                    requestCallback.onFailed(msg);
                }
            }
        });
    }

    @Override
    public void getShopping(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkHttpUtils.getInstance().doGet(Api.BASE_URL + Api.CART_URL, params, new OkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (requestCallback != null) {
                    requestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (requestCallback != null) {
                    requestCallback.onFailed(msg);
                }
            }
        });
    }
}
