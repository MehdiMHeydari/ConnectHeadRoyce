package com.example.connectheadroycedesignproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GPSActivity  extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private TextView txtLocation;
    private int locationRequestCode = 1000;
    private double wayLatitude = 0.0, wayLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpspage);

        client = LocationServices.getFusedLocationProviderClient(this);
        this.txtLocation = (TextView) findViewById(R.id.location);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);

        } else {
            // already permission granted
        }


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

                        if (location != null) {
                            wayLatitude = location.getLatitude();
                            wayLongitude = location.getLongitude();
                            txtLocation.setText(String.format(Locale.US, "%s -- %s", wayLatitude, wayLongitude));
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


