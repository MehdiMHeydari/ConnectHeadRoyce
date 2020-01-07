package com.example.connectheadroycedesignproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);

        int tologin = R.id.tologin;
        Button loginbutton = findViewById(tologin);
        loginbutton.setTag(tologin);
        loginbutton.setOnClickListener(new tologin());
    }

    private class tologin implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));




        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    String URL = "http://10.40.1.200:8080/register";
                    HashMap<String, String> server = new HashMap<String, String>();
                    server.put("userkey", "myusername");
                    server.put("passkey", "mypassword");



                    String Method = "POST";
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



