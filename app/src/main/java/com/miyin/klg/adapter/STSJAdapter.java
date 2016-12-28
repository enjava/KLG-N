package com.miyin.klg.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.miyin.klg.R;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QY on 2016/12/23 0023.
 */

public class STSJAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> list = new ArrayList<>();
    private final int HEAD = 0x1111;
    private final int FOOT = 0x2222;

    public STSJAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new ViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stsj_head, parent, false));
        } else {
            return new ViewHolder(parent.getContext(), LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stsj, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else {
            return FOOT;
        }
    }
}
