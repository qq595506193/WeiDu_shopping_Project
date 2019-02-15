package com.changhao.weidu_shopping_demo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.ui.base.BaseActivity;
import com.changhao.weidu_shopping_demo.ui.fragment.Fragment_Circle;
import com.changhao.weidu_shopping_demo.ui.fragment.Fragment_Home;
import com.changhao.weidu_shopping_demo.ui.fragment.Fragment_Indent;
import com.changhao.weidu_shopping_demo.ui.fragment.Fragment_Mine;
import com.changhao.weidu_shopping_demo.ui.fragment.Fragment_Shopping;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.rg_01)
    RadioGroup rg_01;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        rg_01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdBtn_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rdBtn_circle:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rdBtn_shopping:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rdBtn_indent:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rdBtn_mine:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        rg_01.check(R.id.rdBtn_home);
                        break;
                    case 1:
                        rg_01.check(R.id.rdBtn_circle);
                        break;
                    case 2:
                        rg_01.check(R.id.rdBtn_shopping);
                        break;
                    case 3:
                        rg_01.check(R.id.rdBtn_indent);
                        break;
                    case 4:
                        rg_01.check(R.id.rdBtn_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new Fragment_Home();
                    case 1:
                        return new Fragment_Circle();
                    case 2:
                        return new Fragment_Shopping();
                    case 3:
                        return new Fragment_Indent();
                    case 4:
                        return new Fragment_Mine();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_main;
    }
}
