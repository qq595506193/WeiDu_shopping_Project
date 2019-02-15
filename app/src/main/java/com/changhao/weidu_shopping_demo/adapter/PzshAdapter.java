package com.changhao.weidu_shopping_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PzshAdapter extends XRecyclerView.Adapter<PzshAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXXES;

    public PzshAdapter(Context context) {
        commodityListBeanXXES = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeanXXES(List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXXES) {
        if (commodityListBeanXXES != null) {
            this.commodityListBeanXXES = commodityListBeanXXES;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PzshAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pzsh_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PzshAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(commodityListBeanXXES.get(i).getMasterPic()).into(viewHolder.iv_pzsh_icon);
        viewHolder.tv_pzsh_name.setText(commodityListBeanXXES.get(i).getCommodityName());
        viewHolder.tv_pzsh_price.setText("￥" + commodityListBeanXXES.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityListBeanXXES == null ? 0 : commodityListBeanXXES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_pzsh_icon;
        private final TextView tv_pzsh_name;
        private final TextView tv_pzsh_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pzsh_icon = itemView.findViewById(R.id.iv_pzsh_icon);
            tv_pzsh_name = itemView.findViewById(R.id.tv_pzsh_name);
            tv_pzsh_price = itemView.findViewById(R.id.tv_pzsh_price);
        }
    }
}
