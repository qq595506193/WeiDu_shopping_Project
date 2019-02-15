package com.changhao.weidu_shopping_demo.net;

public interface OkHttpCallback {
    void onSuccess(String result);

    void onFailed(String msg);
}
