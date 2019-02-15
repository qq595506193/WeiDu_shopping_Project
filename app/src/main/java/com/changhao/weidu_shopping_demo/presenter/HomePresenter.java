package com.changhao.weidu_shopping_demo.presenter;

import com.changhao.weidu_shopping_demo.bean.BannerBean;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.changhao.weidu_shopping_demo.contract.HomeContract;
import com.changhao.weidu_shopping_demo.model.MyModel;
import com.changhao.weidu_shopping_demo.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class HomePresenter extends HomeContract.HomePresenter {

    private MyModel myModel;
    private HomeContract.IHomeView iHomeView;

    public HomePresenter(HomeContract.IHomeView iHomeView) {
        myModel = new MyModel();
        this.iHomeView = iHomeView;
    }

    @Override
    public void getBanner(HashMap<String, String> params) {
        myModel.getBanner(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                List<BannerBean.ResultBean> result1 = bannerBean.getResult();
                if (iHomeView != null) {
                    iHomeView.onBannerSuccess(result1);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iHomeView != null) {
                    iHomeView.onFailed(msg);
                }
            }
        });
    }


    @Override
    public void getHome(HashMap<String, String> params) {
        myModel.getHome(params, new RequestCallback() {
            @Override
            public void onSuccess(String result) {
                HomeBean homeBean = new Gson().fromJson(result, HomeBean.class);
                if (iHomeView != null) {
                    iHomeView.onRxxpHomeSuccess(homeBean.getResult().getRxxp());
                    iHomeView.onMlssHomeSuccess(homeBean.getResult().getMlss());
                    iHomeView.onPzshHomeSuccess(homeBean.getResult().getPzsh());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iHomeView != null) {
                    iHomeView.onFailed(msg);
                }
            }
        });
    }
}
