package com.android.fastcampus.kwave.plot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.fastcampus.kwave.plot.DataSource.Records;
import com.android.fastcampus.kwave.plot.DetailActivity;
import com.android.fastcampus.kwave.plot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-04.
 * 메인 페이지의 랭킹 6~10위 리스트를 만드는 RecyclerView Adapter
 */

public class RankRecyclerAdapterMain extends RecyclerView.Adapter<RankRecyclerAdapterMain.Holder> {
    private List<Records> data = new ArrayList<>();
    //    private List<Data> data1 = new ArrayList<>();
    Context context = null;
    private LayoutInflater inflater;
    private ImageView rankRecyclerViewImageMain;
    private TextView textStarMain;
    private TextView textTitleMain;
    private TextView textDateStartMain;
    private TextView textFromToMain;
    private TextView textDateEndMain;
    private TextView textExhibitionMain;

    public RankRecyclerAdapterMain(List<Records> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Records> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            this.context = parent.getContext();
        }
        View view = inflater.inflate(R.layout.list_item_main_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Records records = data.get(position);
//        Data bbs = data1.get(position);
//        holder.setTextTitle(bbs.title);
//        holder.setTextDateEnd(bbs.date_end);
//        holder.setTextExhibition(bbs.exhibition);
        holder.textTitleMain.setText(records.getTitle());
        holder.textDateStartMain.setText(records.getStartdate());
        holder.textDateEndMain.setText(records.getEnddate());
        holder.textExhibitionMain.setText(records.getLocation());
//        holder.setImage(datas.rankBestImage[position+5]);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textTitleMain,textDateStartMain,textFromToMain,textDateEndMain,textExhibitionMain;
        private RatingBar starMain;
        private ImageView rankRecyclerViewImageMain;

        public Holder(View v) {
            super(v);
            rankRecyclerViewImageMain = (ImageView) v.findViewById(R.id.rankRecyclerViewImage_main);
            starMain = (RatingBar) v.findViewById(R.id.star_main);
            textTitleMain = (TextView) v.findViewById(R.id.textTitle_main);
            textDateStartMain = (TextView) v.findViewById(R.id.textDateStart_main);
            textFromToMain = (TextView) v.findViewById(R.id.textFromTo_main);
            textDateEndMain = (TextView) v.findViewById(R.id.textDateEnd_main);
            textExhibitionMain = (TextView) v.findViewById(R.id.textExhibition_main);            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("POSITION", position);
                    intent.putExtra("fromList", data.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }


        public void setImage(int position) {
            this.rankRecyclerViewImageMain.setImageResource(position);
        }
    }
}
