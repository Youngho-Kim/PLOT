package com.android.fastcampus.kwave.plot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.fastcampus.kwave.plot.CommentActivity;
import com.android.fastcampus.kwave.plot.DataSource.Data;
import com.android.fastcampus.kwave.plot.DataSource.Loader;
import com.android.fastcampus.kwave.plot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2017-08-07.
 */

public class WantAdapter extends RecyclerView.Adapter<WantAdapter.Holder> {

    Context context;
    List<Data> dataList = new ArrayList<>();

    public WantAdapter(List<Data> dataList, Context context){
        this.dataList = Loader.getData(context);
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Data datas = dataList.get(position);
        holder.textTitle_main.setText(datas.title);
        holder.textDateStart_main.setText(datas.date_start);
        holder.textDateEnd_main.setText(datas.date_end);
        holder.textExhibition_main.setText(datas.exhibition);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        int position;
        TextView textTitle_main, textDateStart_main, textDateEnd_main, textExhibition_main;

        public Holder(View itemView) {
            super(itemView);

            textTitle_main = (TextView) itemView.findViewById(R.id.textTitle_main);
            textDateStart_main = (TextView) itemView.findViewById(R.id.textDateStart_main);
            textDateEnd_main = (TextView) itemView.findViewById(R.id.textDateEnd_main);
            textExhibition_main = (TextView) itemView.findViewById(R.id.textExhibition_main);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CommentActivity.class);
                    intent.putExtra("LIST_POSITION", position);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
