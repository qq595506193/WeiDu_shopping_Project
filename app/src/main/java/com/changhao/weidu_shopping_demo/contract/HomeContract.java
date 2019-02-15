package com.changhao.weidu_shopping_demo.contract;

import com.changhao.weidu_shopping_demo.bean.BannerBean;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.changhao.weidu_shopping_demo.net.RequestCallback;

import java.util.HashMap;
import java.util.List;

public interface HomeContract {
    abstract class HomePresenter {

        public abstract void getBanner(HashMap<String, String> params);

        public abstract void getHome(HashMap<String, String> params);

    }

    interface IHomeMdel {
        void getHome(HashMap<String, String> params, RequestCallback requestCallback);

        void getBanner(HashMap<String, String> params, RequestCallback requestCallback);
    }

    interface IHomeView {
        void onBannerSuccess(List<BannerBean.ResultBean> resultBeans);

        void onRxxpHomeSuccess(List<HomeBean.ResultBean.RxxpBean> rxxpBeans);

        void onMlssHomeSuccess(List<HomeBean.ResultBean.MlssBean> mlssBeans);

        void onPzshHomeSuccess(List<HomeBean.ResultBean.PzshBean> pzshBeans);

        void onFailed(String msg);
    }
}
