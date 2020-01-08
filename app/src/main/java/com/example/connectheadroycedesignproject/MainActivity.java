package com.example.connectheadroycedesignproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {

    private  EditText user;
    private  EditText password;
    private Button login;
    private String userdata2;
    private String passworddata2;

    private String BASE_URL = "https://10.40.1.228:8880/register?username=" + userdata2 + "&password=" + passworddata2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        //userdata2 = user.getText().toString();
        //passworddata2 = password.getText().toString();

        int login = R.id.login;
        Button loginbutton = findViewById(login);
        loginbutton.setTag(login);
        loginbutton.setOnClickListener(new HandleButton());


        int toregister = R.id.toregister;
        Button registerbutton = findViewById(toregister);
        registerbutton.setTag(toregister);
        registerbutton.setOnClickListener(new toregister());

    }

    private class toregister implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, GPSActivity.class));

        }


    }

    private class HandleButton implements View.OnClickListener {


        //waits for click
        @Override
        public void onClick(View v) {
            System.out.println("hi");
            String userdata = user.getText().toString();
            String passworddata = password.getText().toString();
            System.out.println(userdata + " " + passworddata);


            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String URL = "http://10.40.1.190:8080/login";
                        HashMap<String, String> server = new HashMap<String, String>();
                        server.put("userkey", "myusername");
                        server.put("passkey", "mypassword");



                        String Method = "GET";
                        String URLEncodedData = "";
                        for (String param : server.keySet()) {

                            // Add params of the format: param=value&params=value2
                            URLEncodedData += server + "=" + server.get(server) + "&";
                        }
                        if (URLEncodedData.length() > 0){
                            URLEncodedData = URLEncodedData.substring(0, URLEncodedData.length() - 1);
                        }

                        if (Method.equals(("GET"))) {
                            // Start the GET query
                            URL += "?";
                            URL += URLEncodedData;
                        }



                        java.net.URL url = new URL(URL);
                        HttpURLConnection connection = null;

                        if(URL.startsWith("https")) {
                            connection = (HttpsURLConnection) url.openConnection();

                            try {
                                // Create the SSL connection
                                SSLContext sc;
                                sc = SSLContext.getInstance("TLS");
                                sc.init(null, null, new java.security.SecureRandom());
                                ((HttpsURLConnection)connection).setSSLSocketFactory(sc.getSocketFactory());
                            }catch(NoSuchAlgorithmException e ){
                                // code to run if the security algorithm doesn't exist
                            }
                            catch(KeyManagementException e ) {
                                // code to run if the key is incorrect

                            }

                        }
                        else {
                            // Unencrypted HTTP connection
                            connection = (HttpURLConnection) url.openConnection();
                        }

                        try{
                            connection.setRequestMethod(Method);
                            connection.setDoInput(true);

                            if(Method.equals("POST")){
                                // Tell the connection we're sending data TO THE SERVER
                                connection.setDoOutput(true);

                                // We're sending the data url-encoded (as opposed to JSON encoded)
                                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                // We're using regular characters
                                connection.setRequestProperty("charset", "UTF-8");
                                connection.setUseCaches(false);

                                // Send data to the server!
                                PrintWriter writer = new PrintWriter(connection.getOutputStream());
                                writer.print(URLEncodedData);

                                // Force sending the data and close the output
                                writer.flush();
                                writer.close();
                            }

                            Scanner Serverinfo = new Scanner(connection.getInputStream());

                            while(Serverinfo.hasNextLine() ){
                                String nextLine = Serverinfo.nextLine();
                                //Output to LogCat
                                Log.d("FROM SERVER", nextLine);

                            }
                        }finally {
                            connection.disconnect();
                        }

                    } catch (MalformedURLException e) {
                        // Code that runs if URL is in the incorrect format
                    } catch (IOException e) {
                        // Code that runs if the URL doesn't exist
                        Log.d("ERROR", "IOEXCEPTION: " + e.toString());
                    }

                }


            });
            thread.start();
        }
    }




}



