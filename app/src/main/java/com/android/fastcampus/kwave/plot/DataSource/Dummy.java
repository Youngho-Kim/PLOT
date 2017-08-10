package com.android.fastcampus.kwave.plot.DataSource;

/**
 * Created by maxx on 2017. 8. 10..
 */

public class Dummy {

    private Records[] records;

    public Records[] getRecords ()
    {
        return records;
    }

    public void setRecords (Records[] records)
    {
        this.records = records;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [records = "+records+"]";
    }
}
