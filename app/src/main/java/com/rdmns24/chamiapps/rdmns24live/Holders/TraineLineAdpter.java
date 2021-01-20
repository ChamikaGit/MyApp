package com.rdmns24.chamiapps.rdmns24live.Holders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rdmns24.chamiapps.rdmns24live.Models.TrainLines;
import com.rdmns24.chamiapps.rdmns24live.R;

import java.util.List;

/**
 * Created by fidenz on 4/4/18.
 */

public class TraineLineAdpter extends RecyclerView.Adapter<TraineLineAdpter.LineViewholder> {


    List<TrainLines.DataBean> dataBeans;
    Context context;
    mGetpostion mGetpostion;

    public TraineLineAdpter(List<TrainLines.DataBean> dataBeans, Context context, TraineLineAdpter.mGetpostion mGetpostion) {
        this.dataBeans = dataBeans;
        this.context = context;
        this.mGetpostion = mGetpostion;
    }




    @Override
    public LineViewholder onCreateViewHolder(ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cell_lines, parent, false);
        return new LineViewholder(view);

    }

    @Override
    public void onBindViewHolder(LineViewholder holder, int position) {

        holder.tvLine.setText(dataBeans.get(position).getTrainLine());
        holder.tvLineSinhala.setText(dataBeans.get(position).getTrainLineSinhala());
        holder.tvLineDescription.setText(dataBeans.get(position).getTrainLineDiscription());


    }

    @Override
    public int getItemCount() {
        return (dataBeans != null ? dataBeans.size() : 0);
    }


    public class LineViewholder extends RecyclerView.ViewHolder {

        TextView tvLine;
        TextView tvLineSinhala;
        TextView tvLineDescription;
        RelativeLayout relLine;


        public LineViewholder(View itemView) {
            super(itemView);

            tvLine = itemView.findViewById(R.id.idline);
            tvLineSinhala =itemView.findViewById(R.id.lineSinhala);
            tvLineDescription =itemView.findViewById(R.id.lineDescription);
            relLine=itemView.findViewById(R.id.idRelativeLine);

            relLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position= getAdapterPosition();
                    mGetpostion.getposition(position);
                }
            });
        }
    }





    public interface mGetpostion {

        void getposition(int NewsItemposition);


    }
}
