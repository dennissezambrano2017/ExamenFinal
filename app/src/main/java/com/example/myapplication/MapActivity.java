package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.io.Serializable;

public class MapActivity extends AppCompatActivity implements Serializable {

    Country country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent i = getIntent();
        country = (Country) i.getSerializableExtra("countryData");
        cargarMap();
    }
    private void cargarMap(){
        try {

            ImageView imgflag = (ImageView) this.findViewById(R.id.imagenBandera);
            TextView textInfo =(TextView) this.findViewById(R.id.content);
            Map mapManager = new Map(MapActivity.this, country, imgflag,textInfo);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapView);
            mapFragment.getMapAsync(mapManager);

        }
        catch(Exception E){
            E.printStackTrace();
        }
    }
}