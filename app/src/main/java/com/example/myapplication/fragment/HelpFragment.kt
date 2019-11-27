package com.example.myapplication.fragment


import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R


class HelpFragment : Fragment() {

    lateinit var mView: View

    lateinit var helpCall : RelativeLayout
    lateinit var helpEmail : RelativeLayout
    lateinit var helpFacebook: RelativeLayout
    lateinit var helpTwitter: RelativeLayout
    lateinit var helpShare: RelativeLayout
    private val MY_PERMISSIONS_REQUEST_PHONE = 111

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_help, container, false)

        helpCall = mView.findViewById(R.id.rl_help_call)
        helpEmail = mView.findViewById(R.id.rl_help_email)
        helpFacebook = mView.findViewById(R.id.rl_help_facebook)
        helpTwitter = mView.findViewById(R.id.rl_help_twitter)
        helpShare = mView.findViewById(R.id.rl_help_share)

        helpCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.CALL_PHONE) !== PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), MY_PERMISSIONS_REQUEST_PHONE)
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:+91 8849011856")
                try {
                    startActivity(callIntent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(context!!.applicationContext, "Call failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        helpEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND, Uri.parse("mailto:"))

            emailIntent.type = "message/rfc822"

            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("binjalprajapati07@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry/Feedback")

            try { // the user can choose the email client
                startActivity(Intent.createChooser(emailIntent, "Choose an email client from..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(activity, "No email client configured!", Toast.LENGTH_LONG).show()
            }
        }

        helpFacebook.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/H7SRmT"))
            startActivity(browserIntent)
        }

        helpTwitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/"))
             startActivity(browserIntent)
        }

        helpShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody =
                "Hey, Checkout how I keep myself updated with all the startup News using Start-O-Preneur!\n" + "https://www.google.com/"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Your startup update")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        return mView
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_PHONE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    val tm =
                        context!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    if (tm.phoneType == TelephonyManager.PHONE_TYPE_NONE) { //No calling functionality
                        Toast.makeText(context!!.applicationContext, "Call failed", Toast.LENGTH_SHORT).show()
                    } else { //calling functionality
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.data = Uri.parse("tel:+91 8849011856")
                        try {
                            startActivity(callIntent)
                        } catch (ex: ActivityNotFoundException) {
                            Toast.makeText(context!!.applicationContext, "Call failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }
}
