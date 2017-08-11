package com.android.fastcampus.kwave.plot;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.fastcampus.kwave.plot.DataSource.Records;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    String detailKey;
    Bundle bundle;
    Records records;
    LatLng latLng;
    Marker marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        myMap = googleMap;
        Intent intent = getIntent();
        Log.i("intent" , "=================intent=============="+intent);
        records.setLocation(intent.getExtras().getString("fromDetail"));
        Log.i("records.setLocation" , "=================intent=============="+records.getLocation());
        latLng = findGeoPoint(records.getLocation());
        if (latLng != null) {
            marker = myMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(records.getLocation())
                    .icon(BitmapDescriptorFactory.defaultMarker()));
            marker.showInfoWindow();

        }
    }

    public LatLng findGeoPoint(String address) {
        Geocoder coder = new Geocoder(this);
        List<Address> addr = null;
        try {
            addr = coder.getFromLocationName(address, 1);// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address lating = addr.get(0);
        double lat = lating.getLatitude(); // 위도가져오기
        double lon = lating.getLongitude(); // 경도가져오기
        latLng = new LatLng(lat, lon);
        return latLng;
    }
}
