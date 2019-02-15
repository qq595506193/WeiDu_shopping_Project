package com.changhao.weidu_shopping_demo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.adapter.ShoppingAdapter;
import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.callback.ShoppingUiCallback;
import com.changhao.weidu_shopping_demo.contract.ShoppingContract;
import com.changhao.weidu_shopping_demo.presenter.ShoppingPresenter;
import com.changhao.weidu_shopping_demo.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Shopping extends BaseFragment implements ShoppingContract.IShoppingView, ShoppingUiCallback, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_01)
    XRecyclerView rv_01;
    @BindView(R.id.ck_qx)
    CheckBox ck_qx;
    @BindView(R.id.tv_settle)
    TextView tv_settle;
    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;


    private ShoppingAdapter shoppingAdapter;
    private ShoppingPresenter shoppingPresenter;
    List<ShoppingBean.Cart> carts;
    private int page = 1;

    @Override
    protected int getViewResId() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        shoppingPresenter.getShopping(params);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        shoppingPresenter = new ShoppingPresenter(this);
        rv_01.setLayoutManager(new LinearLayoutManager(getActivity()));
        shoppingAdapter = new ShoppingAdapter(getActivity());
        shoppingAdapter.setShoppingCallback(this);//初始化回调接口
        rv_01.setAdapter(shoppingAdapter);

        initClick();
    }

    /**
     * 点击事件
     */
    private void initClick() {
        carts = new ArrayList<>();

        ck_qx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (ShoppingBean.Cart cart : carts) {
                        cart.isChecked = true;// 设置一级商家选中
                        for (ShoppingBean.Cart.Product product : cart.list) {
                            product.isProductChecked = true;
                        }
                    }
                } else {
                    for (ShoppingBean.Cart cart : carts) {
                        cart.isChecked = false;// 设置一级商家选中
                        for (ShoppingBean.Cart.Product product : cart.list) {
                            product.isProductChecked = false;
                        }
                    }
                }

                getTotalPrice();
                shoppingAdapter.notifyDataSetChanged();


            }


        });

    }

    private void getTotalPrice() {
        double totalPrice = 0;
        // 遍历所有商品计算总价
        for (ShoppingBean.Cart cart : shoppingAdapter.getCarts()) {
            for (ShoppingBean.Cart.Product product : cart.list) {
                if (product.isProductChecked) {
                    System.out.println("product.productNum:" + product.productNum);
                    totalPrice += product.price * product.productNum;
                }
            }
        }
        tv_totalPrice.setText("￥：" + totalPrice);

    }


    @Override
    public void onShoppingSuccess(List<ShoppingBean.Cart> carts) {
        for (ShoppingBean.Cart cart : carts) {
            for (ShoppingBean.Cart.Product product : cart.list) {
                product.productNum = 1;
            }
        }
        this.carts = carts;
        if (carts != null) {
            shoppingAdapter.setCarts(carts);

            rv_01.refreshComplete();
        } else {
            shoppingAdapter.addCarts(carts);

            rv_01.loadMoreComplete();
        }
    }


    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void notifyShopping() {
        getTotalPrice();
    }

    @Override
    public void onRefresh() {
        rv_01.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        rv_01.loadMoreComplete();
    }
}
