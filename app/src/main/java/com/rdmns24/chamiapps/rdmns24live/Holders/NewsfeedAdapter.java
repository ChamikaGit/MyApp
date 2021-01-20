package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.Newsfeed;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fidenz on 3/30/18.
 */

public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.NewsPostHolder> {


    List<Newsfeed.DataBean> dataBeanList;
    private Context context;
    private Getpostion getpostion;

    public NewsfeedAdapter(List<Newsfeed.DataBean> dataBeanList, Context context, Getpostion getpostion) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.getpostion = getpostion;
    }

    @Override
    public NewsPostHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cell, parent, false);
        return new NewsPostHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsPostHolder holder, int position) {


        String newsimagesample = "http://www.rdmns.lk/app/images/nws.jpg";

        holder.tvTitle.setText(dataBeanList.get(position).getPostTitle());
        holder.tvDescription.setText(dataBeanList.get(position).getPostDescription());
        holder.tvDate.setText(dataBeanList.get(position).getUpdatedDatetime());
        Picasso.with(context).load(Consts.IMAGE_PATH + dataBeanList.get(position).getPostImage()).placeholder(R.drawable.icon).into(holder.imgTumb);


//        getpostion
//        getpostion.getposition(position);


    }

    @Override
    public int getItemCount() {

        return (dataBeanList != null ? dataBeanList.size() : 0);

    }

    public class NewsPostHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;
        ImageView imgTumb;
        RelativeLayout RelNewsfeed;


        public NewsPostHolder(View itemView) {
            super(itemView);


            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvDate = itemView.findViewById(R.id.tvdate);
            this.imgTumb = itemView.findViewById(R.id.profile_image);
            this.RelNewsfeed = itemView.findViewById(R.id.RelativelayoutNewsfeed);


            RelNewsfeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    getpostion.getposition(position);

                }
            });

        }
    }


    public interface Getpostion {

        void getposition(int NewsItemposition);


    }
}
