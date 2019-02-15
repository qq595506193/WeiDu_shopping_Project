package com.changhao.weidu_shopping_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.changhao.weidu_shopping_demo.R;
import com.changhao.weidu_shopping_demo.bean.HomeBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {

    private Context context;
    private List<HomeBean.ResultBean.RxxpBean> rxxpBeans;
    private List<HomeBean.ResultBean.MlssBean> mlssBeans;
    private List<HomeBean.ResultBean.PzshBean> pzshBeans;

    private final int RXXP_ITEM = 0;
    private final int MLSS_ITEM = 1;
    private final int PZSH_ITEM = 2;

    RxxpViewHolder rxxpViewHolder;
    MlssViewHolder mlssViewHolder;
    PzshViewHolder pzshViewHolder;
    private RxxpAdapter rxxpAdapter;
    private MlssAdapter mlssAdapter;
    private PzshAdapter pzshAdapter;

    public HomeAdapter(Context context) {
        rxxpBeans = new ArrayList<>();
        mlssBeans = new ArrayList<>();
        pzshBeans = new ArrayList<>();
        this.context = context;
    }

    public void setRxxpBeans(List<HomeBean.ResultBean.RxxpBean> rxxpBeans) {
        if (rxxpBeans != null) {
            this.rxxpBeans = rxxpBeans;
        }
        notifyDataSetChanged();
    }

    public void setMlssBeans(List<HomeBean.ResultBean.MlssBean> mlssBeans) {
        if (mlssBeans != null) {
            this.mlssBeans = mlssBeans;
        }
        notifyDataSetChanged();
    }

    public void setPzshBeans(List<HomeBean.ResultBean.PzshBean> pzshBeans) {
        if (pzshBeans != null) {
            this.pzshBeans = pzshBeans;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == RXXP_ITEM) {
            return RXXP_ITEM;
        } else if (position == MLSS_ITEM) {
            return MLSS_ITEM;
        } else {
            return PZSH_ITEM;
        }
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if (getItemViewType(i) == RXXP_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_rxxp, viewGroup, false);
            rxxpViewHolder = new RxxpViewHolder(view);
            return rxxpViewHolder;
        } else if (getItemViewType(i) == MLSS_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mlss, viewGroup, false);
            mlssViewHolder = new MlssViewHolder(view);
            return mlssViewHolder;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_pzsh, viewGroup, false);
            pzshViewHolder = new PzshViewHolder(view);
            return pzshViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
        if (rxxpBeans.size() > 0 && mlssBeans.size() > 0 && pzshBeans.size() > 0) {
            if (getItemViewType(i) == RXXP_ITEM) {
                rxxpViewHolder.tv_rxxp.setText(rxxpBeans.get(0).getName());
                rxxpViewHolder.rxxp_rv.setLayoutManager(new GridLayoutManager(context, 3));
                rxxpAdapter = new RxxpAdapter(context);
                rxxpViewHolder.rxxp_rv.setAdapter(rxxpAdapter);
                rxxpAdapter.setCommodityListBeans(rxxpBeans.get(0).getCommodityList());
            } else if (getItemViewType(i) == MLSS_ITEM) {
                mlssViewHolder.tv_mlss.setText(mlssBeans.get(0).getName());
                mlssViewHolder.mlss_rv.setLayoutManager(new LinearLayoutManager(context));
                mlssAdapter = new MlssAdapter(context);
                mlssViewHolder.mlss_rv.setAdapter(mlssAdapter);
                mlssAdapter.setCommodityListBeanXXES(mlssBeans.get(0).getCommodityList());
            } else {
                pzshViewHolder.tv_pzsh.setText(pzshBeans.get(0).getName());
                pzshViewHolder.pzsh_rv.setLayoutManager(new GridLayoutManager(context, 2));
                pzshAdapter = new PzshAdapter(context);
                pzshViewHolder.pzsh_rv.setAdapter(pzshAdapter);
                pzshAdapter.setCommodityListBeanXXES(pzshBeans.get(0).getCommodityList());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class RxxpViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_rxxp;
        private final XRecyclerView rxxp_rv;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rxxp = itemView.findViewById(R.id.tv_rxxp);
            rxxp_rv = itemView.findViewById(R.id.rxxp_rv);
        }
    }

    class MlssViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_mlss;
        private final XRecyclerView mlss_rv;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_mlss = itemView.findViewById(R.id.tv_mlss);
            mlss_rv = itemView.findViewById(R.id.mlss_rv);
        }
    }

    class PzshViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_pzsh;
        private final XRecyclerView pzsh_rv;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pzsh = itemView.findViewById(R.id.tv_pzsh);
            pzsh_rv = itemView.findViewById(R.id.pzsh_rv);
        }
    }
}
