package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Map implements OnMapReadyCallback, GoogleMap.OnMapClickListener{
    private GoogleMap mMap;
    private Context context;
    private Country country;
    private ImageView imgBand;
    private TextView info;

    public Map(Context ctx, Country country, ImageView imgflag,TextView infoCont) {
        this.context = ctx;
        this.country = country;
        this.imgBand = imgflag;
        this.info = infoCont;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       if(googleMap!=null) {
           try {
               mMap = googleMap;
               mMap.getUiSettings().setZoomControlsEnabled(true);

               //mMap.setMapType(4);
               CargarDatos();

           } catch (Exception E) {
               E.printStackTrace();
           }
       }

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public void CargarDatos() {
        Picasso.with(context)
                .load(country.getLinkban())
                .into(imgBand);
        info.setText("Capital: "+country.getNameCapital()+"\n"
                +"Code ISO 2: "+ country.getCodeISO2()+"\n"
                +"Code ISO Num: "+ country.getCodeISONum()+"\n"
                +"Code ISO 3: "+ country.getCodeISO3()+"\n"
                +"Code FIPS: "+ country.getCodeFIPS()+"\n"
                +"Teléfono: "+ country.getTelPrefix()+"\n"
                +"Center: " + country.getCenter0()+" "+country.getCenter1()+"\n"
                +"Rectangle: O= " + country.getGeoWest()+",E= "+country.getGeoEast()+",N= "
                +country.getGeoNorth()+",S= "+country.getGeoSouth());
        if (country != null) {
            double West = country.getGeoWest();
            double East = country.getGeoEast();
            double North = country.getGeoNorth();
            double South = country.getGeoSouth();

            PolylineOptions polyPais = new PolylineOptions()
                    .clickable(false)
                    .add(new LatLng(North, West),//0 0
                            new LatLng(North, East),//0 1
                            new LatLng(South, East),// 1 1
                            new LatLng(South, West),//1 0
                            new LatLng(North, West));//0 0
            polyPais.color(Color.MAGENTA);
            polyPais.width(5);

            mMap.addPolyline(polyPais);

            //mover camara

            LatLng pais = new LatLng(Double.parseDouble(country.getCenter0()),
                    Double.parseDouble(country.getCenter1()));

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(pais)
                    .zoom(3)
                    .bearing(0)      //noreste arriba
                    .build();

            CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);

            mMap.animateCamera(camUpd3);
        }
    }



}
