package com.android.fastcampus.kwave.plot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RankRecyclerAdapterMain extends RecyclerView.Adapter<RankRecyclerAdapterMain.Holder>{
    private List<Records> data = new ArrayList<>();
    private LayoutInflater inflater;

    public RankRecyclerAdapterMain(List<Records> data, Context context) {
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Records> data){
        this.data = data;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_main_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Records records = data.get(position);
        holder.setTextTitle(records.getTitle());
        holder.setTextDateEnd(records.getEnddate());
        holder.setTextExhibition(records.getLocation());
//        holder.setImage(records.rankBestImage[position+5]);
        holder.setPosition(position);
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private TextView textTitle;
        private TextView textDateEnd;
        private TextView textExhibition;
        private ImageView rankRecyclerViewImage_main;
        public Holder(View v) {
            super(v);
            textTitle = (TextView) v.findViewById(R.id.textTitle_main);
            textDateEnd = (TextView) v.findViewById(R.id.textDateEnd_main);
            textExhibition = (TextView) v.findViewById(R.id.textExhibition_main);
            rankRecyclerViewImage_main = (ImageView) v.findViewById(R.id.rankRecyclerViewImage_main);
            v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(v.getContext(), DetailActivity.class);
                     intent.putExtra("ListId", "MainActivity");
                     intent.putExtra("LIST_POSITION", position);
                     intent.putExtra("fromMain", data.get(position));
                     v.getContext().startActivity(intent);
                 }
            });
        }
        public void setPosition(int position){
            this.position = position;
        }


        public String getTextTitle() {
            return textTitle.getText().toString();
        }

        public void setTextTitle(String title) {
            textTitle.setText(title);
        }

        public String getTextDateEnd() {
            return textDateEnd.getText().toString();
        }

        public void setTextDateEnd(String dateEnd) {
            textDateEnd.setText(dateEnd);
        }

        public String getTextExhibition() {
            return textExhibition.getText().toString();
        }

        public void setTextExhibition(String exhibition) {
            textExhibition.setText(exhibition);
        }

        public ImageView getRankRecyclerViewImage_main() {
            return rankRecyclerViewImage_main;
        }

        public void setImage(int position) {
            this.rankRecyclerViewImage_main.setImageResource(position);
        }
    }
}
