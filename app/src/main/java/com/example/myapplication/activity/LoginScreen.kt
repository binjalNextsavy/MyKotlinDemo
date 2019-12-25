package com.example.myapplication.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.myapplication.model.User
import com.example.myapplication.model.UserLogin
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginScreen : AppCompatActivity() {

    lateinit var userName: EditText
    lateinit var userPassword: EditText
    lateinit var forRecyclerBtn: Button
    lateinit var forRetrofit: Button
    lateinit var refresh: Button

    lateinit var showHide : Button

    lateinit var checkInternet: CardView
    lateinit var constraintLayout: ConstraintLayout

    lateinit var myRef: DatabaseReference
    lateinit var userList: List<User>
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        myRef = FirebaseDatabase.getInstance().getReference("User")
        userList = ArrayList<User>()
        refresh = findViewById(R.id.refresh)

        showHide = findViewById(R.id.btnShowHilde)

        //For backpressed
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "Login"

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this,SplashScreen ::class.java)
            startActivity(intent)
        }
        userName = findViewById<EditText>(R.id.name)
        userPassword = findViewById(R.id.password)

        forRecyclerBtn = findViewById(R.id.recyclerbtn)
        forRecyclerBtn.isVisible = false

        forRetrofit = findViewById(R.id.retrofitbtn)
        forRetrofit.isVisible = false

        checkInternet = findViewById(R.id.cardInternet)
        checkInternet.isVisible = false

        constraintLayout = findViewById(R.id.constraint)
        constraintLayout.isVisible = false

        if (!isInternetAvailable(this)) {
            checkInternet.isVisible = true
            refresh.setOnClickListener {
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)

            }

        } else {
            constraintLayout.isVisible = true
        }


        //Show/Hide Password

        showHide.setOnClickListener {
            if (showHide.text.toString().equals("Show")){
                userPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showHide.text = "Hide"

            } else {
                userPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                showHide.text = "Show"
            }
        }

        findViewById<Button>(R.id.login).setOnClickListener {

            if (savedData()) {

                //registerLogin(userName.text.toString(),userPassword.text.toString())

               val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

                val editor = mypref.edit()

                editor.putString("Name", userName.text.toString())
                editor.putString("Password", userPassword.text.toString())

                editor.apply()

                Toast.makeText(this, "Data Saved in preference", Toast.LENGTH_LONG).show()
                intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)


                val id = myRef.push().key

                val user = UserLogin(userName.text.toString(), userPassword.text.toString())

                if (id != null) {
                    myRef.child(id).setValue(user)
                }



                Toast.makeText(this@LoginScreen, "Data Saved in database", Toast.LENGTH_SHORT).show()



                userName.setText("")
                userPassword.setText("")

            }

        }

        retrivedDate()


    }

    //For api
  /* private fun registerLogin(userName: String,userPassword: String) {
        //val mCallback: ResponseCallback = ResponseCallback

        var userLogin = UserLogin(userName,userPassword)
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)

       val call: Call<UserLogin> = service.userSignIn(userLogin,ResponseCallback()

           override fun success(final Response response) {

           }
       ))

       call.enqueue(object : Callback<UserLogin> {

            override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                if(response.code() == 200){
                    Toast.makeText(this@LoginScreen,"Login Successfull",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                //Toast.makeText(this@LoginScreen,"" + t.message,Toast.LENGTH_LONG).show()
            }
        })
   }*/

    private fun savedData(): Boolean {
        val passwordRegex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\\S+${'$'}).{8,}""".toRegex()
        if(userName.text.isEmpty()){
            userName.error = "Please Enter Name"
            userName.requestFocus()
            return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userName.text).matches()){
                userName.error = "Please Enter valid Email"
                userName.requestFocus()
                return false
        }

        //PATTERNS
/*
        if (android.util.Patterns.PHONE )
            if (android.util.Patterns.IP_ADDRESS)
                if(android.util.Patterns.WEB_URL)

                if (android.util.Patterns.DOMAIN_NAME)
*/


        //Password regex



        else if(userPassword.text.isEmpty()){
            userPassword.error = "Please Enter Password"
            userName.requestFocus()
            return false
        }
        /*else if(!passwordRegex.matches(userPassword.text)){
            userPassword.error = "Please Enter Valid Password"
            userPassword.requestFocus()
            return false
        }*/
        else if(userPassword.length() < 8){
            userPassword.error = "Please enter valid password"
            userPassword.requestFocus()
            return false
        }

        else {
            forRecyclerBtn.isVisible = false
            forRetrofit.isVisible = false

            return true
        }





    }

    private fun retrivedDate(){
        var myref = getSharedPreferences("mypref",Context.MODE_PRIVATE)

        val nameOf = myref.getString("Name","")
        val passwordOf = myref.getString("Password","")

        userName.setText(nameOf)
        userPassword.setText(passwordOf)
    }

    private fun isInternetAvailable(activity: Activity) : Boolean{


        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected


    }

    companion object{
        var BaseUrl = "https://reqres.in/"
    }
}
