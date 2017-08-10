package com.android.fastcampus.kwave.plot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.fastcampus.kwave.plot.DataSource.Dummy;
import com.android.fastcampus.kwave.plot.adapter.ListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.android.fastcampus.kwave.plot.MainActivity.url;
import static com.android.fastcampus.kwave.plot.ThrowData2Activity.task;

public class ListActivity extends AppCompatActivity implements IData {
    RecyclerView recycler;
    ListAdapter adapter;
    List<Dummy> datas = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        task(this);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        adapter = new ListAdapter();
        adapter.setData(datas);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void postExecute(String jsonString) {
        Gson gson = new Gson();
        Dummy dum = gson.fromJson(jsonString, Dummy.class);
        datas.add(dum);
        Log.i("datas", "============datas============="+datas);
    }

    @Override
    public String getUrl() {
        return url;
    }
}
