package com.changhao.weidu_shopping_demo.bean;

import java.util.List;

public class ShoppingBean {
    public String code;
    public String msg;
    public List<Cart> data;

    public class Cart {
        public boolean isChecked;// 一级列表是否勾选
        public String sellerName;
        public String sellerid;
        public List<Product> list;

        public class Product {
            public boolean isProductChecked;// 二级列表是否勾选
            public String title;
            public String images;
            public String pid;
            public double price;
            public int pos;
            public int productNum = 1;
        }
    }
}
