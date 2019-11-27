package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.activity.LoginScreen
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class VerifyPhoneActivity : AppCompatActivity() {
    var editTextCode: EditText? = null
    private var mVerificationId: String? = null
    var mAuth: FirebaseAuth? = null
    var progressBar: ProgressBar? = null
    var checkedTvl: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        editTextCode = findViewById(R.id.editTextCode)
        progressBar = findViewById(R.id.progressbar)
        checkedTvl = findViewById(R.id.btncheckedtv)
        val i = intent
        val mobile = i.getStringExtra("mobile")
        //sendVerificationCode(mobile)
        findViewById<View>(R.id.buttonSignIn).setOnClickListener(View.OnClickListener {
            val code = editTextCode!!.getText().toString().trim { it <= ' ' }
            if (code.isEmpty() || code.length < 6) {
                editTextCode!!.setError("Please Enter Valid Code")
                editTextCode!!.requestFocus()
                return@OnClickListener
            }
            verifyVerificationCode(code)
        })
        checkedTvl!!.text = "Checked Textview"
        checkedTvl!!.setOnClickListener(View.OnClickListener {
            if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
                        supportFragmentManager.beginTransaction()
                                .add(R.id.fragment_container, DemoCheckedTv())
                                .commit();
                    }
        })
    }

    private fun sendVerificationCode(mobile: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mobile",  // Phone number to verify
            60,  // Timeout duration
            TimeUnit.SECONDS,  // Unit of timeout
            this,  // Activity (for callback binding)
            mCallbacks
        )
    }

    private val mCallbacks: OnVerificationStateChangedCallbacks = object : OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            val code = phoneAuthCredential.smsCode
            if (code != null) {
                progressBar!!.visibility = View.INVISIBLE
                editTextCode!!.setText(code)
                verifyVerificationCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@VerifyPhoneActivity, e.message, Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(s, forceResendingToken)
            mVerificationId = s
            Log.e("SMS", "MSG $mVerificationId")
        }
    }

    private fun verificationCode(code: String) {
        val phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId!!, code)
        signInWithPhoneAuthCredential(phoneAuthCredential)
    }

    private fun verifyVerificationCode(code: String) {
        try { //creating the credential
            val credential = PhoneAuthProvider.getCredential(mVerificationId!!, code)
            //signing the user
            signInWithPhoneAuthCredential(credential)
        } catch (e: Exception) {
            Toast.makeText(this, "Verification code is invalid", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    val i = Intent(this@VerifyPhoneActivity, LoginScreen::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                    // ...
                } else { // Sign in failed, display a message and update the UI
                    val message = "Somthing is wrong, we will fix it soon..."
                    task.exception
                    val snackbar =
                        Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG)
                    snackbar.setAction("Dismiss") { }
                    snackbar.show()
                }
            }
    }
}



//Java
/*public class VerifyPhoneActivity extends AppCompatActivity {

    EditText editTextCode;
    private String mVerificationId;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button checkedTvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);
        progressBar = findViewById(R.id.progressbar);

        checkedTvl = findViewById(R.id.btncheckedtv);

        Intent i = getIntent();
        String mobile = i.getStringExtra("mobile");

        sendVerificationCode(mobile);

        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Please Enter Valid Code");
                    editTextCode.requestFocus();
                    return;
                }

                verifyVerificationCode(code);

            }
        });

        checkedTvl.setText("Checked Textview");

        checkedTvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, DemoCheckedTv())
                            .commit();
                }*/
            }
        });


    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                progressBar.setVisibility(View.INVISIBLE);
                editTextCode.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            Log.e("SMS","MSG "+mVerificationId);
        }
    };

    private void verificationCode(String code) {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, code);

        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void verifyVerificationCode(String code) {

       try{
           //creating the credential
           PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

           //signing the user
           signInWithPhoneAuthCredential(credential);
       } catch (Exception e){
           Toast.makeText(this, "Verification code is invalid", Toast.LENGTH_SHORT).show();
       }

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(VerifyPhoneActivity.this, LoginScreen.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            String message = "Somthing is wrong, we will fix it soon...";

                            task.getException();

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                }
                            });
                            snackbar.show();
                        }
                    }
                });
    }


}
*/