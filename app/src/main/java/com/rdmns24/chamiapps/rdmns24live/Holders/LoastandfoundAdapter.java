package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdmns24.chamiapps.rdmns24live.Models.LostfoundItem;
import com.rdmns24.chamiapps.rdmns24live.R;

import java.util.ArrayList;
import java.util.List;

public class LoastandfoundAdapter extends RecyclerView.Adapter<LoastandfoundAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LostfoundItem> LostfoundItemList;
    private LayoutInflater inflater;
    private GetLoastItemPosition getLoastItemPosition;

    public LoastandfoundAdapter(Context context, ArrayList<LostfoundItem> lostfoundItemList,GetLoastItemPosition getLoastItemPosition) {
        this.context = context;
        LostfoundItemList = lostfoundItemList;
        this.inflater = LayoutInflater.from(context);
        this.getLoastItemPosition = getLoastItemPosition;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public LoastandfoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.row_lostfound_categorie, parent, false);
        LoastandfoundAdapter.ViewHolder viewHolder = new LoastandfoundAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        LostfoundItem lostfoundItem  = getItem(position);
        holder.tvlost_found_name.setText(lostfoundItem.getName());
        holder.tvlost_found_cat_name.setText(lostfoundItem.getCategorie_name());
        holder.rel_main_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoastItemPosition.getposition(position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return (LostfoundItemList != null ? LostfoundItemList.size() : 0);
    }

    public LostfoundItem getItem(int position) {
        return LostfoundItemList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvlost_found_name;
        public TextView tvlost_found_cat_name;
        public RelativeLayout rel_main_cat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvlost_found_name = itemView.findViewById(R.id.tvlost_found_name);
            tvlost_found_cat_name = itemView.findViewById(R.id.tvlost_found_cat_name);
            rel_main_cat = itemView.findViewById(R.id.rel_main_cat);
        }

    }

    public interface GetLoastItemPosition {

        void getposition(int LostItemposition);


    }
}
