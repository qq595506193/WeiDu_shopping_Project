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

public class RxxpAdapter extends XRecyclerView.Adapter<RxxpAdapter.ViewHolder> {

    private Context context;
    private List<HomeBean.ResultBean.RxxpBean.CommodityListBean> commodityListBeans;

    public RxxpAdapter(Context context) {
        commodityListBeans = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeans(List<HomeBean.ResultBean.RxxpBean.CommodityListBean> commodityListBeans) {
        if (commodityListBeans != null) {
            this.commodityListBeans = commodityListBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RxxpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rxxp_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(commodityListBeans.get(i).getMasterPic()).into(viewHolder.iv_rxxp_icon);
        viewHolder.tv_rxxp_name.setText(commodityListBeans.get(i).getCommodityName());
        viewHolder.tv_rxxp_price.setText("￥" + commodityListBeans.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityListBeans == null ? 0 : commodityListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_rxxp_icon;
        private final TextView tv_rxxp_name;
        private final TextView tv_rxxp_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_rxxp_icon = itemView.findViewById(R.id.iv_rxxp_icon);
            tv_rxxp_name = itemView.findViewById(R.id.tv_rxxp_name);
            tv_rxxp_price = itemView.findViewById(R.id.tv_rxxp_price);
        }
    }
}
