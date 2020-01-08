package com.example.connectheadroycedesignproject;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GPSActivity  extends AppCompatActivity {

    private FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpspage);

        client = LocationServices.getFusedLocationProviderClient(this);

        Button button = findViewById(R.id.getLocation);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                requestPermission();

                System.out.println(ActivityCompat.checkSelfPermission(GPSActivity.this, ACCESS_FINE_LOCATION));
                if(ActivityCompat.checkSelfPermission(GPSActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                    return;

                }


                client.getLastLocation().addOnSuccessListener(GPSActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        System.out.println(location);


                        if (location!= null){
                            TextView textView= findViewById(R.id.location);
                            textView.setText(location.toString());
                        }

                    }
                });

            }
        });

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
