package com.example.connectheadroycedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText User;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User = findViewById (R.id.user);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

    }

    public class ButtonHandler implements View.OnClickListener {

        //waits for click
        @Override
        public void onClick(View v) {




        }



    }
}
