package com.changhao.weidu_shopping_demo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.contract.LoginContract;
import com.changhao.weidu_shopping_demo.presenter.LoginPresenter;
import com.changhao.weidu_shopping_demo.ui.base.BaseActivity;
import com.changhao.weidu_shopping_demo.utils.RegularUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements LoginContract.ILoginView {

    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    @BindView(R.id.tv_imLogin)
    TextView tv_imLogin;
    @BindView(R.id.btn_reg)
    Button btn_reg;
    private LoginPresenter myPresenter;


    @Override
    protected void initData() {

        initClick();
    }

    private void initClick() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ed_phone.getText().toString().trim();
                String pwd = ed_pwd.getText().toString().trim();
                if (!RegularUtils.isMobileExact(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);
                myPresenter.getRegister(params);
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        myPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_register;
    }

    @Override
    public void onLoginSuccess(String result) {

    }

    @Override
    public void onRegisterSuccess(String result) {
        if (result.equals("0000")) {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @Override
    public void onFailed(String msg) {

    }
}
