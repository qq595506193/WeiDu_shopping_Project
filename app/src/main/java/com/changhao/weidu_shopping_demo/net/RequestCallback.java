package com.changhao.weidu_shopping_demo.net;

public interface RequestCallback {
    void onSuccess(String result);

    void onFailed(String msg);
}
