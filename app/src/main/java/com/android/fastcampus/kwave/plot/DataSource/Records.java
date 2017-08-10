package com.android.fastcampus.kwave.plot.DataSource;

import java.io.Serializable;

/**
 * Created by maxx on 2017. 8. 10..
 */

public class Records implements Serializable{
    private String parking;

    private String phonenumber_info;

    private String location;

    private String address_road;

    private String freeornot;

    private String homepage;

    private String hostagency;

    private String content;

    private String title;

    private String duration;

    private String startdate;

    private String price;

    private String age;

    private String mainagency;

    private String seats;

    private String base_date;

    private String enddate;

    public String getParking ()
    {
        return parking;
    }

    public void setParking (String parking)
    {
        this.parking = parking;
    }

    public String getPhonenumber_info ()
    {
        return phonenumber_info;
    }

    public void setPhonenumber_info (String phonenumber_info)
    {
        this.phonenumber_info = phonenumber_info;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getAddress_road ()
    {
        return address_road;
    }

    public void setAddress_road (String address_road)
    {
        this.address_road = address_road;
    }

    public String getFreeornot ()
    {
        return freeornot;
    }

    public void setFreeornot (String freeornot)
    {
        this.freeornot = freeornot;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getHostagency ()
    {
        return hostagency;
    }

    public void setHostagency (String hostagency)
    {
        this.hostagency = hostagency;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getStartdate ()
    {
        return startdate;
    }

    public void setStartdate (String startdate)
    {
        this.startdate = startdate;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getMainagency ()
    {
        return mainagency;
    }

    public void setMainagency (String mainagency)
    {
        this.mainagency = mainagency;
    }

    public String getSeats ()
    {
        return seats;
    }

    public void setSeats (String seats)
    {
        this.seats = seats;
    }

    public String getBase_date ()
    {
        return base_date;
    }

    public void setBase_date (String base_date)
    {
        this.base_date = base_date;
    }

    public String getEnddate ()
    {
        return enddate;
    }

    public void setEnddate (String enddate)
    {
        this.enddate = enddate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [parking = "+parking+", phonenumber_info = "+phonenumber_info+", location = "+location+", address_road = "+address_road+", freeornot = "+freeornot+", homepage = "+homepage+", hostagency = "+hostagency+", content = "+content+", title = "+title+", duration = "+duration+", startdate = "+startdate+", price = "+price+", age = "+age+", mainagency = "+mainagency+", seats = "+seats+", base_date = "+base_date+", enddate = "+enddate+"]";
    }
}
