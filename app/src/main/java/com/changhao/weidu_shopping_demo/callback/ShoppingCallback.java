package com.changhao.weidu_shopping_demo.callback;

public interface ShoppingCallback {
    void notifyShoppingItem(boolean isChecked, int position);

    void notifyNum();
}
