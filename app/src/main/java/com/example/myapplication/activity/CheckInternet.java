package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import com.example.myapplication.R;

public class CheckInternet extends AppCompatActivity {

    Button refresh;
    CardView checkInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        refresh = findViewById(R.id.refresh1);
        checkInternet = findViewById(R.id.cardInternet1);
        checkInternet.setVisibility(View.GONE);

        if(!isInternetAvailable(this)){
            checkInternet.setVisibility(View.VISIBLE);

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });
        }
        else {
            Intent i = new Intent(this,LoginScreen.class);
            startActivity(i);
        }
    }


    public static boolean isInternetAvailable(Activity activity) {

        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        } else {
//            Toast.makeText(activity, "Check your internet connection and try again.", Toast.LENGTH_LONG).show();
//            AppUtilUI.showToast(this, "Check your internet connection and try again.");

            ///AppUtilUI.showSnackbar(activity,activity.getResources().getString(R.string.check_error_try_again));


            return false;
        }
    }
}
