package com.changhao.weidu_shopping_demo.ui.fragment;

import android.view.View;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.ui.base.BaseFragment;

import butterknife.ButterKnife;

public class Fragment_Mine extends BaseFragment {
    @Override
    protected int getViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }
}
