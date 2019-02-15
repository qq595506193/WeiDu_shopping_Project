package com.changhao.weidu_shopping_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.bean.ShoppingBean;
import com.changhao.weidu_shopping_demo.callback.ShoppingCallback;
import com.changhao.weidu_shopping_demo.callback.ShoppingUiCallback;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends XRecyclerView.Adapter<ShoppingAdapter.ViewHolder> implements ShoppingCallback {

    private Context context;
    private List<ShoppingBean.Cart> carts;
    private ChildShoppingAdapter childShoppingAdapter;

    private ShoppingUiCallback shoppingCallback;

    public void setShoppingCallback(ShoppingUiCallback shoppingCallback) {
        this.shoppingCallback = shoppingCallback;
    }

    public ShoppingAdapter(Context context) {
        carts = new ArrayList<>();
        this.context = context;
    }

    public void setCarts(List<ShoppingBean.Cart> carts) {
        if (carts != null) {
            this.carts = carts;
        }
        notifyDataSetChanged();
    }

    public void addCarts(List<ShoppingBean.Cart> carts) {
        if (carts != null) {
            this.carts.addAll(carts);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_shopping, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingAdapter.ViewHolder viewHolder, int i) {
        final ShoppingBean.Cart cart = carts.get(i);


        viewHolder.tv_title.setText(cart.sellerName);// 设置商家名字
        viewHolder.ck_02.setChecked(cart.isChecked);

        // 对每件商品的POS赋值，记录一级列表的位置
        for (ShoppingBean.Cart.Product product : cart.list) {
            product.pos = i;
        }

        viewHolder.rv_02.setLayoutManager(new LinearLayoutManager(context));// 布局管理器
        childShoppingAdapter = new ChildShoppingAdapter(context);// 二级列表适配器
        childShoppingAdapter.setShoppingCallback(this);
        viewHolder.rv_02.setAdapter(childShoppingAdapter);// 设置适配器
        childShoppingAdapter.setProducts(cart.list);// 设置数据的传递


        viewHolder.ck_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.isChecked = viewHolder.ck_02.isChecked();// 设置一级对象的选中状态
                // 设置二级选中状态
                for (ShoppingBean.Cart.Product product : cart.list) {
                    product.isProductChecked = cart.isChecked;

                }
                notifyDataSetChanged();
                // 选中状态改变后，通知首页价格联动
                if (shoppingCallback != null) {
                    shoppingCallback.notifyShopping();
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return carts == null ? 0 : carts.size();
    }

    /**
     * 二级列表选中状态监听，通知一级列表刷新数据
     *
     * @param isChecked
     * @param position
     */
    @Override
    public void notifyShoppingItem(boolean isChecked, int position) {
        // 设置一级列表选中状态
        carts.get(position).isChecked = isChecked;
        notifyDataSetChanged();

        // 选中状态改变后,通知主页联动价格
        if (shoppingCallback != null) {
            shoppingCallback.notifyShopping();
        }
    }

    /**
     * 最新数据
     *
     * @return
     */
    public List<ShoppingBean.Cart> getCarts() {
        return carts;
    }

    /**
     * 数量改变后通知价格联动
     */
    @Override
    public void notifyNum() {
        if (shoppingCallback != null) {
            shoppingCallback.notifyShopping();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox ck_02;
        private final TextView tv_title;
        private final XRecyclerView rv_02;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ck_02 = itemView.findViewById(R.id.ck_02);
            tv_title = itemView.findViewById(R.id.tv_title);
            rv_02 = itemView.findViewById(R.id.rv_02);
        }
    }
}
