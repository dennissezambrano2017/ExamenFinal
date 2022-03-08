package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ml.Model;

import org.checkerframework.common.util.report.qual.ReportOverride;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView Result;
    Button camara, galeria;
    ImageView imagenVi;
    int imageSize = 224;private
    RequestQueue requestQueue;
    Country country;
    Button buttonMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country = new Country();
        Result = findViewById(R.id.Name);
        camara = findViewById(R.id.btn_TomarFoto);
        galeria = findViewById(R.id.button2);
        imagenVi = findViewById(R.id.image);
        buttonMap = (Button) findViewById(R.id.map);
        buttonMap.setVisibility(View.GONE);
        camara.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent camaraInten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camaraInten, 3);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }

        });
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camaraInten = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(camaraInten, 1);
            }

        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map(country);
            }
        });

        requestQueue = Volley.newRequestQueue(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                Bitmap imagen = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(imagen.getWidth(), imagen.getHeight());
                imagen = ThumbnailUtils.extractThumbnail(imagen, dimension, dimension);
                imagenVi.setImageBitmap(imagen);

                imagen = Bitmap.createScaledBitmap(imagen, imageSize, imageSize, false);
                classifyImage(imagen);
            } else {
                Uri dat = data.getData();
                Bitmap imagen = null;
                try {
                    imagen = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imagenVi.setImageBitmap(imagen);

                imagen = Bitmap.createScaledBitmap(imagen, imageSize, imageSize, false);
                classifyImage(imagen);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void classifyImage(Bitmap images) {
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(images);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(image);
            //List<Category> probability = outputs.getProbabilityAsCategoryList();
            float[] probability = outputs.getProbabilityAsTensorBuffer().getFloatArray();
            int maxPos = 0;
            float maxPosibility = 0;
            for (int i = 0; i < probability.length; i++) {
                if (probability[i] > maxPosibility) {
                    maxPosibility = probability[i];
                    maxPos = i;
                }
            }
            String[] classe = {"AR","BE","BR","CO","CR","EC","ES","FR","GB","JP","MX","PT","SE","UY"};

            stringRequest(classe[maxPos]);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
    public void stringRequest(String inicial){
        Log.d("name","si ingresa"+inicial);
        String url ="http://www.geognos.com/api/en/countries/info/all.json";
        ArrayList<String> lstDatos = new ArrayList<String>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonArray = response.getJSONObject("Results");
                            JSONObject jsonArray2 = jsonArray.getJSONObject(inicial);
                            country.setName(jsonArray2.getString("Name"));
                            country.setTelPrefix(jsonArray2.getString("TelPref"));
                            JSONArray jsonArrayGtp = jsonArray2.getJSONArray("GeoPt");
                            country.setCenter0(jsonArrayGtp.getString(0));
                            country.setCenter1(jsonArrayGtp.getString(1));

                            JSONObject countryCodes = jsonArray2.getJSONObject("CountryCodes");
                            country.setCodeISO2(countryCodes.getString("iso2"));
                            country.setCodeISO3(countryCodes.getString("iso3"));
                            country.setCodeISONum(countryCodes.getString("isoN"));
                            country.setCodeFIPS(countryCodes.getString("fips"));

                            JSONObject capital = jsonArray2.getJSONObject("Capital");
                            country.setNameCapital(capital.getString("Name"));

                            JSONObject geoRectangle = jsonArray2.getJSONObject("GeoRectangle");
                            country.setGeoWest(Double.parseDouble(geoRectangle.getString("West")));
                            country.setGeoEast(Double.parseDouble(geoRectangle.getString("East")));
                            country.setGeoNorth(Double.parseDouble(geoRectangle.getString("North")));
                            country.setGeoSouth(Double.parseDouble(geoRectangle.getString("South")));

                            country.setLinkban("http://www.geognos.com/api/en/countries/flag/"+inicial+".png");
                            Result.setKeyListener(null);
                            Result.setText("Nombre del Pais: "+country.getName());
                            buttonMap.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
    private void Map(Country countryObj){
        try {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            intent.putExtra("countryData",countryObj);
            startActivity(intent);
        }catch (Exception E){
            E.printStackTrace();
        }

    }

}