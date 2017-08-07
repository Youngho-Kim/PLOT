package com.android.fastcampus.kwave.plot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.fastcampus.kwave.plot.DataSource.Data;
import com.android.fastcampus.kwave.plot.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recycler;
    ListAdapter adapter;
    List<Data> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        adapter = new ListAdapter();
        adapter.setData(datas);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

}
