package com.example.connectheadroycedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static EditText user;
    public static EditText password;
    private Button login;
    static String userdata2 = user.getText().toString();
    static String passworddata2 = password.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user);
       password = findViewById(R.id.password);




        int login = R.id.login;
        Button loginbutton = findViewById(login);
        loginbutton.setTag(login);
        loginbutton.setOnClickListener(new Login());

    }




    public class Login implements View.OnClickListener {


        //waits for click
        @Override
        public void onClick(View v) {

            String userdata = user.getText().toString();
            String passworddata = password.getText().toString();
            System.out.println(userdata + " " + passworddata);


        }


    }

    public static class UserData {

        public static String GetUser(){
            return userdata2;
        }

        public static String GetPassword(){
            return passworddata2;
        }

    }




}


