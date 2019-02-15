package com.changhao.weidu_shopping_demo.contract;

import com.changhao.weidu_shopping_demo.bean.BannerBean;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.net.RequestCallback;

import java.util.HashMap;
import java.util.List;

public interface ShoppingContract {
    abstract class ShoppingPresenter {
        public abstract void getShopping(HashMap<String, String> params);

    }


    interface IShoppingMolde {
        void getShopping(HashMap<String, String> params, RequestCallback requestCallback);
    }

    interface IShoppingView {
        void onShoppingSuccess(List<ShoppingBean.Cart> carts);

        void onFailed(String msg);
    }


}
