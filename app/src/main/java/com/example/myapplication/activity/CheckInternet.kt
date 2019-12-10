package com.example.myapplication.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.activity.LoginScreen
import java.util.*

class CheckInternet : AppCompatActivity() {
    var refresh: Button? = null
    var checkInternet: CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_internet)

        //when Internet is off its show
        //Spinner of country
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        var country: String
        for (loc in locales) {
            country = loc.displayCountry
            if (country.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        Collections.sort(countries, java.lang.String.CASE_INSENSITIVE_ORDER)
        val citizenship = findViewById<View>(R.id.spinner) as Spinner
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        citizenship.adapter = adapter
        citizenship.setSelection(adapter.getPosition(DEFAULT_LOCAL))


        refresh = findViewById(R.id.refresh1)
        checkInternet = findViewById(R.id.cardInternet1)
        checkInternet!!.setVisibility(View.GONE)
        if (!isInternetAvailable(this)) {
            checkInternet!!.setVisibility(View.VISIBLE)
            refresh!!.setOnClickListener(View.OnClickListener {
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            })
        } else {
            val i = Intent(this, LoginScreen::class.java)
            startActivity(i)
        }
    }

    companion object {
        private const val DEFAULT_LOCAL = "India"

        fun isInternetAvailable(activity: Activity): Boolean {
            val connectivityManager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                true
            } else { //            Toast.makeText(activity, "Check your internet connection and try again.", Toast.LENGTH_LONG).show();
//            AppUtilUI.showToast(this, "Check your internet connection and try again.");
///AppUtilUI.showSnackbar(activity,activity.getResources().getString(R.string.check_error_try_again));
                false
            }
        }
    }
}