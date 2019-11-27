package com.example.myapplication.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.model.UserName;
import com.example.myapplication.model.UserPhone;
import com.example.myapplication.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebasePhoneAuth extends AppCompatActivity {


    EditText editTextName;
    DatabaseReference myRef;
    List<UserPhone> user;
    ArrayList providers;

    private static final int RC_SIGN_IN = 137;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_phone_auth);

        myRef = FirebaseDatabase.getInstance().getReference("UserName");

        editTextName = findViewById(R.id.editTextName);

        user = new ArrayList<>();

        providers = new ArrayList();

        providers.add(new AuthUI.IdpConfig.EmailBuilder().build());
        providers.add(new AuthUI.IdpConfig.PhoneBuilder().build());
        providers.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        //Add Dependecies for facebook and twitter
        //providers.add(new AuthUI.IdpConfig.FacebookBuilder().build());
        //providers.add(new AuthUI.IdpConfig.TwitterBuilder().build());


        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = editTextName.getText().toString().trim();

                if(Name.isEmpty()){
                    editTextName.setError("Enter Name");
                    editTextName.requestFocus();
                    return;
                }

                String id = myRef.push().getKey();

               // UserPhone userPhone = new UserPhone(id,Name);

                //myRef.child(id).setValue(userPhone);

                UserName userName = new UserName(id,Name);

                myRef.child(id).setValue(userName);

                Toast.makeText(FirebasePhoneAuth.this, "Name Saved", Toast.LENGTH_SHORT).show();

                editTextName.setText("");

                doGoogleVerification();


                /*if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(FirebasePhoneAuth.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);*/

            }
        });

        findViewById(R.id.btnTTS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirebasePhoneAuth.this,TextToSpeechActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btnColorPicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirebasePhoneAuth.this,DemoColorPicker.class);
                startActivity(i);
            }
        });
    }
    public void doGoogleVerification() {

        /*startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.PhoneBuilder().build()))
                        .setLogo(R.mipmap.ic_launcher)
                        .setIsSmartLockEnabled(false,false)
                        .build(), RC_SIGN_IN);*/

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.bg_rounded)
                        .setIsSmartLockEnabled(false,false)
                        .build(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Intent i = new Intent(FirebasePhoneAuth.this,LoginScreen.class);
                startActivity(i);
            }
        }

    }
}
