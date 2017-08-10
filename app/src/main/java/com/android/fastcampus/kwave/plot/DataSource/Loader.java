package com.android.fastcampus.kwave.plot.DataSource;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by kwave on 2017-08-02.
 */

public class Loader {
    public static ArrayList<Records> getData(Context context){
        ArrayList<Records> result = new ArrayList<>();
        for(int i=1 ; i<=5 ; i++){
            Records data = new Records();
            data.getTitle();
            data.getEnddate();
            data.getLocation();
            data.getStartdate();
//            data.get;

//            data.setImage("irene", context);
            result.add(data);
        }
        return result;
    }
}
