package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rdmns24.chamiapps.rdmns24live.Models.NewsfeedRecent;
import com.rdmns24.chamiapps.rdmns24live.R;

import java.util.List;

/**
 * Created by fidenz on 3/30/18.
 */

public class NewsfeedAdapter_horizontall extends RecyclerView.Adapter<NewsfeedAdapter_horizontall.NewsPostHolder> {



    List<NewsfeedRecent.DataBean> dataBeanList;
    private Context context;
    private mGetpostion mgetpostion;

    public NewsfeedAdapter_horizontall(List<NewsfeedRecent.DataBean> dataBeanList, Context context, mGetpostion mgetpostion) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.mgetpostion = mgetpostion;
    }

    @Override
    public NewsPostHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cell_horizontall, parent, false);
        return new NewsPostHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsPostHolder holder, final int position) {

        String sameleimage= "http://www.rdmns.lk/app/images/nws.jpg";

        holder.tvTitle.setText(dataBeanList.get(position).getNotificationTitle());
        //Picasso.with(context).load(Consts.IMAGE_PATH+dataBeanList.get(position).getPostImage()).placeholder(R.drawable.facemen).into(holder.imgTumb);

//     holder.relativeLayouthorizon.setOnLongClickListener(new View.OnLongClickListener() {
//         @Override
//         public boolean onLongClick(View view) {
//
//             mgetpostion.getpositionhorizontall(position);
//             return false;
//         }
//     });

     holder.relativeLayouthorizon.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             mgetpostion.getpositionhorizontall(position);

         }
     });


//



    }

    @Override
    public int getItemCount() {

            return (dataBeanList != null ? dataBeanList.size() : 0);

    }

    public class NewsPostHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView imgTumb;
        RelativeLayout relativeLayouthorizon;


        public NewsPostHolder(View itemView) {
            super(itemView);


            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.imgTumb =itemView.findViewById(R.id.profile_image);
            this.relativeLayouthorizon =itemView.findViewById(R.id.idrelhorizonall);

        }
    }


    public interface mGetpostion{

        void getpositionhorizontall(int NewsItemposition);


    }
}
