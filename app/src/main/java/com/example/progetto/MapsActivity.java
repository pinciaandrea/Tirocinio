package com.example.progetto;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

private GoogleMap mMap;
/* https://www.decodexlab.com/zero/12-1-introduzione-google-maps/ */

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }

@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng firenze = new LatLng(33.785022, 72.721992);
        mMap.addMarker(new MarkerOptions().position(firenze).title("Siamo a Firenze!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firenze,15));
        }
}