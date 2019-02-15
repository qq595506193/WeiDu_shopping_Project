package com.changhao.weidu_shopping_demo.presenter;

import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.contract.ShoppingContract;
import com.changhao.weidu_shopping_demo.model.MyModel;
import com.changhao.weidu_shopping_demo.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class ShoppingPresenter extends ShoppingContract.ShoppingPresenter {
    private MyModel myModel;
    private ShoppingContract.IShoppingView iShoppingView;

    public ShoppingPresenter(ShoppingContract.IShoppingView iShoppingView) {
        myModel = new MyModel();
        this.iShoppingView = iShoppingView;
    }

    @Override
    public void getShopping(HashMap<String, String> params) {
        myModel.getShopping(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                ShoppingBean shoppingBean = new Gson().fromJson(result, ShoppingBean.class);
                List<ShoppingBean.Cart> data = shoppingBean.data;
                if (iShoppingView != null) {
                    iShoppingView.onShoppingSuccess(data);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iShoppingView != null) {
                    iShoppingView.onFailed(msg);
                }
            }
        });
    }
}
