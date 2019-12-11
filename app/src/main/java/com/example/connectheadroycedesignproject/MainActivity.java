package com.example.connectheadroycedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static EditText user;
    public static EditText password;
    private Button login;
    static String userdata2 = user.getText().toString();
    static String passworddata2 = password.getText().toString();

    private String BASE_URL = "https://";


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

    private void requestData(String url) {

            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setmethodvalue("GET");
            requestPackage.setUrlvoid(url);

            Downloader downloader = new Downloader();

            downloader.execute(requestPackage);


    }

    private class Downloader extends AsyncTask<RequestPackage, String, String> {
        @Override
        protected String doInBackground(RequestPackage... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //We need to convert the string in result to a JSONObject
                JSONObject jsonObject = new JSONObject(result);


                String User = jsonObject.getString("ask");



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    }


