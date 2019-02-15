package com.changhao.weidu_shopping_demo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.adapter.HomeAdapter;
import com.changhao.weidu_shopping_demo.bean.BannerBean;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.contract.HomeContract;
import com.changhao.weidu_shopping_demo.presenter.HomePresenter;
import com.changhao.weidu_shopping_demo.presenter.LoginPresenter;
import com.changhao.weidu_shopping_demo.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Home extends BaseFragment implements HomeContract.IHomeView {

    @BindView(R.id.imageBtn_menu)
    ImageButton imageBtn_menu;
    @BindView(R.id.imageBtn_search)
    ImageButton imageBtn_search;
    @BindView(R.id.rv_home)
    XRecyclerView rv_home;
    @BindView(R.id.xBanner)
    XBanner xBanner;
    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;

    @Override
    protected int getViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        homePresenter.getHome(params);

        rv_home.setAdapter(homeAdapter);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        homePresenter = new HomePresenter(this);
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器
        homeAdapter = new HomeAdapter(getActivity());
    }


    @Override
    public void onBannerSuccess(List<BannerBean.ResultBean> resultBeans) {


    }

    @Override
    public void onRxxpHomeSuccess(List<HomeBean.ResultBean.RxxpBean> rxxpBeans) {
        if (rxxpBeans != null) {
            homeAdapter.setRxxpBeans(rxxpBeans);
        }
    }

    @Override
    public void onMlssHomeSuccess(List<HomeBean.ResultBean.MlssBean> mlssBeans) {
        if (mlssBeans != null) {
            homeAdapter.setMlssBeans(mlssBeans);
        }
    }

    @Override
    public void onPzshHomeSuccess(List<HomeBean.ResultBean.PzshBean> pzshBeans) {
        if (pzshBeans != null) {
            homeAdapter.setPzshBeans(pzshBeans);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
