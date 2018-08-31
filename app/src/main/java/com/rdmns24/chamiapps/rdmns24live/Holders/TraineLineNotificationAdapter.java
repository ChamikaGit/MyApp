package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdmns24.chamiapps.rdmns24live.Models.TraineLinesNotifications;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fidenz on 4/10/18.
 */

public class TraineLineNotificationAdapter extends RecyclerView.Adapter<TraineLineNotificationAdapter.LineNotificationViewHolder> {


    List<TraineLinesNotifications.DataBean> dataBeansNotifi;
    Context context;
    Getpostion mGetpostion;

    public TraineLineNotificationAdapter(List<TraineLinesNotifications.DataBean> dataBeansNotifi, Context context, Getpostion mGetpostion) {
        this.dataBeansNotifi = dataBeansNotifi;
        this.context = context;
        this.mGetpostion = mGetpostion;
    }

    @Override
    public LineNotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cell_notification, parent, false);
        return new LineNotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LineNotificationViewHolder holder, final int position) {


        holder.NoficationName.setText(dataBeansNotifi.get(position).getNotificationTitle());
        holder.NotificationDescription.setText(dataBeansNotifi.get(position).getNotificationDescription());
        holder.NotificationTime.setText(dataBeansNotifi.get(position).getCreatedDatetime());
        holder.NotificationmenuLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.NotificationmenuLink);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_notification);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_Openwith:
                                mGetpostion.getposition(position);
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });



        String giventime =dataBeansNotifi.get(position).getCreatedDatetime();

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date mdate =simpleDateFormat.parse(giventime);
            long timeinmillseconds = mdate.getTime();

            String textago = TimeAgo.using(timeinmillseconds);

            holder.NotificationTimeAgo.setText(textago);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.relativeLayoutnotidicationcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetpostion.getpositionnewsdetails(position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return (dataBeansNotifi != null ? dataBeansNotifi.size() : 0);
    }

    public class LineNotificationViewHolder extends RecyclerView.ViewHolder {

        TextView NoficationName;
        TextView NotificationDescription;
        TextView NotificationTime;
        TextView NotificationTimeAgo;
        ImageView NotificationmenuLink;
        RelativeLayout relativeLayoutnotidicationcell;


        public LineNotificationViewHolder(View itemView) {

            super(itemView);

            NoficationName = itemView.findViewById(R.id.idNotifiName);
            NotificationDescription = itemView.findViewById(R.id.idNotifiDescription);
            NotificationTime = itemView.findViewById(R.id.idTimeandDate);
            NotificationTimeAgo = itemView.findViewById(R.id.idTimeAgo);
            NotificationmenuLink = itemView.findViewById(R.id.btnmenu);
            relativeLayoutnotidicationcell =itemView.findViewById(R.id.RelativelayoutNewsfeed);


        }
    }

    public interface Getpostion {

        void getposition(int NewsItemposition);
        void getpositionnewsdetails(int position);

    }


}
