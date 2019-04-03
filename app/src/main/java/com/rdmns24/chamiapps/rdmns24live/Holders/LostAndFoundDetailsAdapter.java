package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rdmns24.chamiapps.rdmns24live.Actvities.LostAndFoundDetailsActvity;
import com.rdmns24.chamiapps.rdmns24live.Helpers.Consts;
import com.rdmns24.chamiapps.rdmns24live.Models.Lostfound;
import com.rdmns24.chamiapps.rdmns24live.Models.Newsfeed;
import com.rdmns24.chamiapps.rdmns24live.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fidenz on 3/30/18.
 */

public class LostAndFoundDetailsAdapter extends RecyclerView.Adapter<LostAndFoundDetailsAdapter.NewsPostHolder> {


    private List<Lostfound.Datum> listLostFound;
    private Context context;
    private Getpostion getpostion;
    private String state;
    private Activity activity;

    public void setState(String state) {
        this.state = state;
    }

    public LostAndFoundDetailsAdapter(List<Lostfound.Datum> listLostFound, Context context, Getpostion getpostion, Activity activity) {
        this.listLostFound = listLostFound;
        this.context = context;
        this.getpostion = getpostion;
        this.activity =activity;
    }


    @Override
    public NewsPostHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cell_lost_and_found, parent, false);
        return new NewsPostHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsPostHolder holder, int position) {

        Lostfound.Datum item = getItem(position);

//        if (item.getItemType().equalsIgnoreCase("Found")) {
            holder.tvTitle.setText(item.getTitle());
            holder.tvDescription.setText(item.getDescription());
            holder.tvDate.setText(item.getItemDate());

//        }

//        if (state.equalsIgnoreCase("found")) {
//            for (int i = 0; i < listLostFound.size(); i++) {
//                if (listLostFound.get(i).getItemType().equalsIgnoreCase("Found")) {
//                    holder.tvTitle.setText(listLostFound.get(i).getTitle());
//                    holder.tvDescription.setText(listLostFound.get(i).getDescription());
//                    holder.tvDate.setText(listLostFound.get(i).getDatetime());
//                }
//            }
//        } else {
//
//            for (int i =0;i<listLostFound.size();i++) {
//                if (listLostFound.get(i).getItemType().equalsIgnoreCase("Lost")) {
//                    holder.tvTitle.setText(listLostFound.get(i).getTitle());
//                    holder.tvDescription.setText(listLostFound.get(i).getDescription());
//                    holder.tvDate.setText(listLostFound.get(i).getDatetime());
//
//                }
//
//            }
//        }
//            if (listLostFound.get(position).getItemType().equalsIgnoreCase("Found")) {
//                holder.tvTitle.setText(listLostFound.get(position).getTitle());
//                holder.tvDescription.setText(listLostFound.get(position).getDescription());
//                holder.tvDate.setText(listLostFound.get(position).getDatetime());
//
//            }if (listLostFound.get(position).getItemType().equalsIgnoreCase("Lost")) {
//                holder.tvTitle.setText(listLostFound.get(position).getTitle());
//                holder.tvDescription.setText(listLostFound.get(position).getDescription());
//                holder.tvDate.setText(listLostFound.get(position).getDatetime());
//
//            }
//
//        }else {
//            if (listLostFound.get(position).getItemType().equalsIgnoreCase("Lost")) {
//                holder.tvTitle.setText(listLostFound.get(position).getTitle());
//                holder.tvDescription.setText(listLostFound.get(position).getDescription());
//                holder.tvDate.setText(listLostFound.get(position).getDatetime());
//
//            }
//        }


//        getpostion
//        getpostion.getposition(position);


    }

    @Override
    public int getItemCount() {

        return (listLostFound != null ? listLostFound.size() : 0);

    }

    public class NewsPostHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;
        RelativeLayout RelativelayoutLoastAndfound;


        public NewsPostHolder(View itemView) {
            super(itemView);


            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvDate = itemView.findViewById(R.id.tvdate);
            this.RelativelayoutLoastAndfound = itemView.findViewById(R.id.RelativelayoutLoastAndfound);


            RelativelayoutLoastAndfound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    getpostion.getposition(position);



                    final Dialog dialog = new Dialog(activity);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_custom_notificationdetails);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    TextView textView = dialog.findViewById(R.id.etNote);
                    TextView textViewheader = dialog.findViewById(R.id.idnotificationtext);
                    ImageView imageViewclose = dialog.findViewById(R.id.btnclose);


                    String textdetails = listLostFound.get(position).getDescription();
                    String header = listLostFound.get(position).getTitle();

                    textViewheader.setText(header);
                    textView.setText(textdetails);

                    imageViewclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                    dialog.show();





                }
            });

        }
    }

    public Lostfound.Datum getItem(int position) {
        return listLostFound.get(position);
    }


    public interface Getpostion {

        void getposition(int NewsItemposition);


    }
}
