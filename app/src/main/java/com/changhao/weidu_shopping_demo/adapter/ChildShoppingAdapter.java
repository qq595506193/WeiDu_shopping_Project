package com.changhao.weidu_shopping_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.callback.ShoppingCallback;
import com.changhao.weidu_shopping_demo.widget.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChildShoppingAdapter extends XRecyclerView.Adapter<ChildShoppingAdapter.ViewHolder> {

    private Context context;
    private List<ShoppingBean.Cart.Product> products;

    public ChildShoppingAdapter(Context context) {
        products = new ArrayList<>();
        this.context = context;
    }

    public void setProducts(List<ShoppingBean.Cart.Product> products) {
        if (products != null) {
            this.products = products;
        }
        notifyDataSetChanged();
    }

    private ShoppingCallback shoppingCallback;

    public void setShoppingCallback(ShoppingCallback shoppingCallback) {
        this.shoppingCallback = shoppingCallback;
    }

    @NonNull
    @Override
    public ChildShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child_shopping, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildShoppingAdapter.ViewHolder viewHolder, int i) {
        final ShoppingBean.Cart.Product product1 = products.get(i);
        String images = product1.images;
        final String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.iv_child_icon);
        viewHolder.tv_child_name.setText(product1.title);
        viewHolder.tv_child_price.setText("￥：" + product1.price);
        viewHolder.ck_03.setChecked(product1.isProductChecked);


        viewHolder.add_minus.setAddMinusCallback(new AddMinusView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                product1.productNum = num;// 商品数量动态改变
                //通知一级列表改变刷新数据
                if (shoppingCallback != null) {
                    shoppingCallback.notifyNum();
                }
            }
        });

        viewHolder.ck_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewHolder.ck_03.isChecked()) {
                    product1.isProductChecked = false;
                    // 一级未选中回调
                    if (shoppingCallback != null) {

                        shoppingCallback.notifyShoppingItem(false, product1.pos);
                    }
                } else {// 二级已选中

                    product1.isProductChecked = true;

                    // 遍历所有数据
                    for (ShoppingBean.Cart.Product product : products) {
                        //判断集合内商品的选中状态，如果未选中
                        if (!product.isProductChecked) {
                            if (shoppingCallback != null) {
                                shoppingCallback.notifyShoppingItem(false, product.pos);
                            }
                            break;
                        }
                        // 如果都选中设置一级为true
                        if (shoppingCallback != null) {
                            shoppingCallback.notifyShoppingItem(true, product.pos);
                        }
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox ck_03;
        private final ImageView iv_child_icon;
        private final TextView tv_child_name;
        private final TextView tv_child_price;
        private final AddMinusView add_minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ck_03 = itemView.findViewById(R.id.ck_03);
            iv_child_icon = itemView.findViewById(R.id.iv_child_icon);
            tv_child_name = itemView.findViewById(R.id.tv_child_name);
            tv_child_price = itemView.findViewById(R.id.tv_child_price);
            add_minus = itemView.findViewById(R.id.add_minus);
        }
    }
}
