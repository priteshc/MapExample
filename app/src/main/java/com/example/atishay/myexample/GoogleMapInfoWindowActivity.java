package com.example.atishay.myexample;

/**
 * Created by Atishay on 07-03-2018.
 */

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleMapInfoWindowActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<LatLng> latlngs = new ArrayList<>();



    private  Dialog dialog,dialog1;

    private Button piller,excavator;

    private String id;
    private String rod;
    private PolygonOptions polygonOptions;
    private LatLng latLng1,latLng2,latLng3,latLng4,latLng5;
    private Polygon polygon;

    private GoogleMap googleMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gmap);
        mapFragment.getMapAsync(this);

       piller = findViewById(R.id.piller);
        excavator = findViewById(R.id.piller1);

        rod = getIntent().getExtras().getString("Road","");

            latlngs.add(new LatLng(18.9872015, 72.82904559999997));
            latlngs.add(new LatLng(19.218331, 72.978090));

            latLng1 = new LatLng(19.009913, 72.842686);

           latLng2 = new LatLng(19.021324, 72.842418);
           latLng3 = new LatLng(19.015847, 72.827997);
           latLng4 = new LatLng(19.026875, 72.855335);
           latLng5 = new LatLng( 19.021324, 72.842418);



        piller.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               OpenPopUpm0();
           }
       });

        excavator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPopUp1m0();
            }
        });

        polygonOptions = new PolygonOptions();


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


     /*   LatLng snowqualmie = new LatLng(18.9872015, 72.82904559999997);

        LatLng snowqualmie1 = new LatLng(19.021324, 72.842417800);*/


    /*    latlngs.add(new LatLng(18.932245, 72.826438));
        latlngs.add(new LatLng(19.079961, 72.898767));
*/


   /*     MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(snowqualmie)
                .title("Snowqualmie Falls")
                .snippet("Snoqualmie Falls is located 25 miles east of Seattle.")
                .icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_BLUE));
*/
        for (LatLng point : latlngs) {

            mMap.addMarker(new MarkerOptions().position(point));

         /*   mMap.addMarker(new MarkerOptions().position(snowqualmie1).title("Dadar"));
*/
        }

        final InfoWindowData info = new InfoWindowData();
        info.setImage("snowqualmie");
        info.setHotel("Hotel : excellent hotels available");
        info.setFood("Food : all types of restaurants available");
        info.setTransport("Reach the site by bus, car and train.");

        InfoWindowData info1 = new InfoWindowData();
        info1.setImage("snowqualmie1");
        info1.setHotel("Hotel : excellent malls available");
        info1.setFood("Food : all types of restaurants available");
        info1.setTransport("Reach the site by bus, car and train.");

        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
      //  mMap.setInfoWindowAdapter(customInfoWindow);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(GoogleMapInfoWindowActivity.this,marker.getId(),Toast.LENGTH_SHORT).show();
              //  marker.setTag(info);

                id = marker.getId();

                return false;

            }
        });

/*
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                polygonOptions.add(latLng);

            }
        });*/




      /* Marker m = mMap.addMarker(markerOptions);
        m.setTag(info);*/
        //  m.showInfoWindow();

       /* m.setTag(info);
        m1.setTag(info1);
*/

       if(rod.equals("Road2"))
       {
           mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngs.get(1)));
       }
       else
       {
           mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngs.get(0)));
       }


        mMap.addPolyline((new PolylineOptions()).add(latlngs.get(0), latLng5,latlngs.get(1)).width(5).color(Color.BLUE).geodesic(true));

        mMap.addPolyline((new PolylineOptions()).add(latlngs.get(0), latLng5).width(5).color(Color.GREEN).geodesic(true));



   /*     mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                Log.v("TestLog", "OnCameraChange: " + cameraPosition);

            }
        });*/

  /* mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
       @Override
       public void onCameraMove() {

           mMap.getCameraPosition();

           Log.v("TestLog", "OnCameraChange: " +  mMap.getCameraPosition());



       }
   });*/



  mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
      @Override
      public void onCameraIdle() {

        //  LatLng latLng= mMap.getCameraPosition().target;

          Log.v("TestLog", "OnCameraChange: " +  mMap.getCameraPosition());

          Toast.makeText(GoogleMapInfoWindowActivity.this,String.valueOf(mMap.getCameraPosition()),Toast.LENGTH_SHORT).show();

      }
  });

  //polygonOptions.add(latLng1);

        polygonOptions.add(latLng1);
        polygonOptions.add(latLng2);
        polygonOptions.add(latLng3);
        polygonOptions.add(latLng4);

        countPolygonPoints();


    }

    public void countPolygonPoints()
    {
        if(polygonOptions.getPoints().size()>3){

            polygonOptions.strokeColor(Color.GREEN);
            polygonOptions.strokeWidth((float) 0.30);
            polygonOptions.fillColor(Color.BLUE);
            polygon = mMap.addPolygon(polygonOptions);

        }

       // String url = getDirectionsUrl(latlngs.get(0), latlngs.get(1));

    }


    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }


    private class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            parserTask.execute(result);

        }


    }



    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
             //   routes = parser.parse(jObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap>> result) {
            ArrayList points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap point = path.get(j);

                 /*   double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);*/

                  //  points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(true);

            }

// Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private void OpenPopUpm0() {

             //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        dialog = new Dialog(GoogleMapInfoWindowActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

        TextView colr,heigh,material,thik,shape;

        dialog.setContentView(R.layout.popup);

        colr = dialog.findViewById(R.id.colr);
        heigh = dialog.findViewById(R.id.hight);
        material = dialog.findViewById(R.id.material);
        thik = dialog.findViewById(R.id.thik);
        shape = dialog.findViewById(R.id.shape);

        if(rod.equals("Road2")) {

            colr.setText("Black");
            heigh.setText("5 feet to 5 feet");
            material.setText("Soil");
            thik.setText("100 mm");
            shape.setText("circle");

        }

            if(id!= null) {
            if (id.equals("m1")) {
                colr.setText("Black");
                heigh.setText("5 feet to 5 feet");
                material.setText("Soil");
                thik.setText("100 mm");
                shape.setText("circle");
            }
            else if(id.equals("m0")){

                colr.setText("Grey");
                heigh.setText("3 feet to 7 feet");
                material.setText("Cement");
                thik.setText("60 mm");
                shape.setText("Rectangular");

            }
        }

        dialog.show();

    }


    private void OpenPopUp1m0() {

        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        dialog1 = new Dialog(GoogleMapInfoWindowActivity.this);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

        TextView depth,board,gap,shap;
        dialog1.setContentView(R.layout.popup1);

        depth = dialog1.findViewById(R.id.depth);
        board = dialog1.findViewById(R.id.boards);
        gap = dialog1.findViewById(R.id.gaps);
        shap = dialog1.findViewById(R.id.shape);

        if(rod.equals("Road2")) {

            depth.setText("6’0”");
            board.setText("550 x 210 mm ");
            gap.setText("100 cm");
            shap.setText("Circle");

        }

            if(id!= null) {
            if (id.equals("m1")) {

                depth.setText("6’0”");
                board.setText("550 x 210 mm ");
                gap.setText("100 cm");
                shap.setText("Circle");

            }
            else if(id.equals("m0")){
                depth.setText("4’0”");
                board.setText("250 x 40 mm ");
                gap.setText("50 cm");
                shap.setText("Rectangular");
            }
        }

        dialog1.show();

    }

}

