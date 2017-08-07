package com.android.fastcampus.kwave.plot;

import android.os.AsyncTask;

import static com.android.fastcampus.kwave.plot.GetDataFromServer.getData;

/**
 * Created by maxx on 2017. 8. 7..
 */

public class ThrowData2Activity {
    public static void Task(final IData idata){
        new AsyncTask<String, Void, String>(){
            String result = null;
            @Override
            protected String doInBackground(String... param) {
                try {
                    result = getData(param[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result) {
                idata.postExecute(result);
            }
        }.execute(idata.getUrl());
    }
}
