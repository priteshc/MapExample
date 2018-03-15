package com.example.atishay.myexample;

/**
 * Created by Atishay on 07-03-2018.
 */

import android.app.Dialog;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
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

import java.util.ArrayList;

public class GoogleMapInfoWindowActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<LatLng> latlngs = new ArrayList<>();

    private  Dialog dialog,dialog1;

    private Button piller,excavator;

    private String id;
    private String rod;


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

         /*   mMap.addMarker(new MarkerOptions().position(snowqualmie1)
                    .title("Dadar"));
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

      }
  });
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

