package com.android.fastcampus.kwave.plot.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.fastcampus.kwave.plot.DataSource.Records;
import com.android.fastcampus.kwave.plot.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExDetailFragment extends Fragment {
    TextView ExplainText;
    ImageView ExplainImg;
    public static Records records;

    public ExDetailFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex_detail, container, false);
        ExplainText = (TextView) view.findViewById(R.id.ExplainText);
        ExplainImg = (ImageView) view.findViewById(R.id.ExplainImg);
        return view;
    }

}
