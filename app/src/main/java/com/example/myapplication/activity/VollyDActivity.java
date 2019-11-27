package com.example.myapplication.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

public class VollyDActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    Button vollyButton;
    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
   // private String url = "https://www.unimedliving.com/api/v2/audios";
    //private String url = "https://www.unimedliving.com/api/v2/articles";
    //private String url = "https://api.androidhive.info/contacts/";
    //private String url = "https://www.unimedliving.com/api/v2/videos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly_d);

        vollyButton = findViewById(R.id.vollyButton);

        vollyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAndRequestResponse();
            }
        });
    }

    private void sendAndRequestResponse() {

        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(VollyDActivity.this, "Response :  " + response.toString(), Toast.LENGTH_SHORT).show();
                Log.e("AAA","Reps"+response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VollyDActivity.this, "Error :  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }
}
