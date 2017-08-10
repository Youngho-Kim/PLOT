package com.android.fastcampus.kwave.plot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fastcampus.kwave.plot.DataSource.Data;
import com.android.fastcampus.kwave.plot.DataSource.Loader;
import com.android.fastcampus.kwave.plot.DataSource.Records;
import com.android.fastcampus.kwave.plot.adapter.WantAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WantFragment extends Fragment {

    RecyclerView recyclerView;
    WantAdapter wantAdapter;
    List<Records> dataList;

    public WantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_want, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Data는 데이터가 들어올 형식만 정해져 있어 Loader.getData를 이용.
        dataList = Loader.getData(getContext());
        wantAdapter = new WantAdapter(dataList, getContext());
        recyclerView.setAdapter(wantAdapter);

        return view;
    }

}
