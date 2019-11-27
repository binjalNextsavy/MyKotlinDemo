package com.example.myapplication.sqlitdemo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

class UserRegisterActivity : AppCompatActivity() {
    lateinit var uId : EditText
    lateinit var uName : EditText
    lateinit var uEmail : EditText
    lateinit var listOfUser : ListView
    lateinit var  myListAdapterFor : MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        uId = findViewById(R.id.u_id)
        uName = findViewById(R.id.u_name)
        uEmail = findViewById(R.id.u_email)

        listOfUser = findViewById(R.id.listViewForUser)

        findViewById<Button>(R.id.regUserSave).setOnClickListener {
            val id = uId.text.toString()
            val name = uName.text.toString()
            val email = uEmail.text.toString()

            val dbHandler = DatabaseHandler(this)
            if (id.trim() != "" && name.trim() != "" && email.trim() != ""){
                val status = dbHandler.addUser(UserRegisterData(Integer.parseInt(id),name,email))
                if(status > - 1){
                    Snackbar.make(it,"Registration Successfully",Snackbar.LENGTH_LONG).show()
                    uId.text.clear()
                    uName.text.clear()
                    uEmail.text.clear()

                } else {
                    Toast.makeText(this,"Id,Name And Email cannot be blank",Toast.LENGTH_LONG).show()
                }
            }
        }

        findViewById<Button>(R.id.regUserView).setOnClickListener {
            val databaseHandler = DatabaseHandler(this)

            val user: List<UserRegisterData> = databaseHandler.viewuser()

            val userArrayId = Array<String>(user.size){"0"}
            val userArrayName = Array<String>(user.size){"null"}
            val userArrayEmail = Array<String>(user.size){"null"}

            var index = 0
            for (u in user){
                userArrayId[index] = u.userId.toString()
                userArrayName[index] = u.userName.toString()
                userArrayEmail[index] = u.userEmail.toString()

                index++
            }

            myListAdapterFor = MyListAdapter(this,userArrayId,userArrayName,userArrayEmail)
            listOfUser.adapter = myListAdapterFor
        }

        findViewById<Button>(R.id.regUserUpdate).setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater

            val dialogView = inflater.inflate(R.layout.update_user_dialog,null)
            dialogBuilder.setView(dialogView)

            val edId = dialogView.findViewById<EditText>(R.id.updateId)
            val edName = dialogView.findViewById<EditText>(R.id.updateName)
            val edEmail = dialogView.findViewById<EditText>(R.id.updateEmail)

            dialogBuilder.setTitle("Update Record")
            dialogBuilder.setMessage("Enter Data below")
            dialogBuilder.setPositiveButton("Update",DialogInterface.OnClickListener { dialog, which ->

                val updateId = edId.text.toString()
                val updateName = edName.text.toString()
                val updateEmail = edEmail.text.toString()

                val databaseHandler = DatabaseHandler(this)

                if(updateId.trim() != "" && updateName.trim() != "" && updateEmail.trim() != ""){
                    val status = databaseHandler.updateUser(UserRegisterData(Integer.parseInt(updateId),updateName,updateEmail))

                    if (status > -1){
                        listOfUser.isVisible = false
                        myListAdapterFor.notifyDataSetChanged()
                        Snackbar.make(it,"Update Successfully",Snackbar.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"Id,Name And Email cannot be blank",Toast.LENGTH_LONG).show()
                    }
                }

            })

            dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->  })

            val b = dialogBuilder.create()
            b.show()
        }

        findViewById<Button>(R.id.regUserDelete).setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater

            val dialogView = inflater.inflate(R.layout.delete_user_dialog,null)
            dialogBuilder.setView(dialogView)

            val dltId = dialogView.findViewById<EditText>(R.id.deleteId)

            dialogBuilder.setTitle("Delete Record")
            dialogBuilder.setMessage("Enter Id For Delete Data")
            dialogBuilder.setPositiveButton("Delete",DialogInterface.OnClickListener { dialog, which ->

                val deleteId = dltId.text.toString()

                val databaseHandler = DatabaseHandler(this)

                if(deleteId.trim() != ""){
                    val status = databaseHandler.deletreUser(UserRegisterData(Integer.parseInt(deleteId),"",""))

                    if (status > -1){
                        listOfUser.isVisible = false
                        myListAdapterFor.notifyDataSetChanged()
                        Snackbar.make(it,"Delete Data Successfully",Snackbar.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"Id cannot be blank",Toast.LENGTH_LONG).show()
                    }
                }
            })

            dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->  })

            val b = dialogBuilder.create()
            b.show()
        }


    }
}
