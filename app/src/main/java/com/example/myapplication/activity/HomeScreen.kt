package com.example.myapplication.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.forNotification.FirebaseNotification2
import com.example.myapplication.forNotification.FirebaseNotificationDemo
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

//import retrofit2.Retrofit

class HomeScreen : AppCompatActivity() {


    var array = arrayOf<String>("ABC","EFG","HIJ","PQR","XYZ")
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Home"

        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)

        val listView : ListView = findViewById<ListView>(R.id.listview)

        listView.adapter = arrayAdapter
        registerForContextMenu(listView)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position)
            val selectedPosition = parent.getItemIdAtPosition(position)

            Snackbar.make(view,"Your Selected Item $selectedItem and position $selectedPosition",Snackbar.LENGTH_LONG).show()
        }




        findViewById<Button>(R.id.refresh).setOnClickListener {
            Snackbar.make(it, "Referesh Data",Snackbar.LENGTH_LONG).show()
            arrayAdapter.notifyDataSetChanged()
        }

        findViewById<Button>(R.id.click).setOnClickListener {

            Snackbar.make(it, "Data Changed",Snackbar.LENGTH_LONG).show()
            array.set(0,"AAA")
            array.set(1,"BBB")

            val builder = AlertDialog.Builder(this)

            builder.setTitle("Data Change")
            builder.setMessage("Sure for Data Change!")

            builder.setPositiveButton("YES"){ dialog: DialogInterface?, which: Int ->
                Snackbar.make(it,"Clicked Yes",Snackbar.LENGTH_LONG).show()
            }

            builder.setNegativeButton("No"){dialog: DialogInterface?, which: Int ->
                Snackbar.make(it,"Clciked No",Snackbar.LENGTH_LONG).show()
            }

            builder.setNeutralButton("Cancel"){dialog: DialogInterface?, which: Int ->
                Snackbar.make(it,"Clicked Cancel",Snackbar.LENGTH_LONG).show()
            }

            val alertDialog : AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        findViewById<Button>(R.id.listviewFor).setOnClickListener {
            intent = Intent(this, CustomListview::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.recyclerbtn).setOnClickListener {

            intent = Intent(this, CustomRecyclerview::class.java)
            startActivity(intent)

        }

        findViewById<Button>(R.id.tabLayoutVbtn).setOnClickListener {
            intent = Intent(this, TabLayoutWithView::class.java)
            startActivity(intent)

        }

        findViewById<Button>(R.id.sharebtn).setOnClickListener {
            intent = Intent(this, DemoWhatsapp::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.notification1).setOnClickListener {
            intent = Intent(this, FirebaseNotificationDemo::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.notification2).setOnClickListener {
            intent = Intent(this, FirebaseNotification2::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnImgStore).setOnClickListener {
            intent = Intent(this,FirebasestoreImgDemo::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.retrofitbtn).setOnClickListener {

            intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnlogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            intent = Intent(this,SplashScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.call -> {
                Toast.makeText(applicationContext,"Call Code", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.sms -> {
                Toast.makeText(applicationContext,"SMS Code", Toast.LENGTH_LONG).show()
                return true
            }
            else ->
                super.onContextItemSelected(item)
        }

    }



    /*fun create(){
        val retrofit : Retrofit.Builder().addConvert
    }*/
}
