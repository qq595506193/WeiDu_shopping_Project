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

public class MlssAdapter extends XRecyclerView.Adapter<MlssAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES;

    public MlssAdapter(Context context) {
        commodityListBeanXXES = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeanXXES(List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES) {
        if (commodityListBeanXXES != null) {
            this.commodityListBeanXXES = commodityListBeanXXES;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MlssAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mlss_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MlssAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(commodityListBeanXXES.get(i).getMasterPic()).into(viewHolder.iv_mlss_icon);
        viewHolder.tv_mlss_name.setText(commodityListBeanXXES.get(i).getCommodityName());
        viewHolder.tv_mlss_price.setText("￥" + commodityListBeanXXES.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityListBeanXXES == null ? 0 : commodityListBeanXXES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_mlss_icon;
        private final TextView tv_mlss_name;
        private final TextView tv_mlss_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_mlss_icon = itemView.findViewById(R.id.iv_mlss_icon);
            tv_mlss_name = itemView.findViewById(R.id.tv_mlss_name);
            tv_mlss_price = itemView.findViewById(R.id.tv_mlss_price);
        }
    }
}
