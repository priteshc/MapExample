package com.example.atishay.myexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Atishay on 09-03-2018.
 */

public class DetailPackage extends AppCompatActivity {

    Button button;
    TextView name,area,city,district;
    String rod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailpackges);

        button = findViewById(R.id.map);

        name =findViewById(R.id.name);
        area =findViewById(R.id.area);
        city =findViewById(R.id.city);
        district =findViewById(R.id.dist);


        if(getIntent().getExtras()!=null){

          rod = getIntent().getExtras().getString("Road","");

            if(rod.equals("Road2")){

                name.setText("IOCL limited");
                area.setText("Thane");
                city.setText("Thane");
                district.setText("Raigad");

            }


        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailPackage.this,GoogleMapInfoWindowActivity.class);
                intent.putExtra("Road",rod);
                startActivity(intent);

            }
        });

    }
}
