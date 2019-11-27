package com.example.myapplication.sqlitdemo

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R


class MyListAdapter(val context: Activity, val id: Array<String>, val name: Array<String>, val email: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list_user,name) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        var rowView = inflater.inflate(R.layout.custom_list_user,null,true)

        val idText = rowView.findViewById<TextView>(R.id.textViewId)
        val nameText = rowView.findViewById<TextView>(R.id.textViewName)
        val emailText = rowView.findViewById<TextView>(R.id.textViewEmail)

        idText.text = "Id: ${id[position]}"
        nameText.text = "Name: ${name[position]}"
        emailText.text = "Email: ${email[position]}"

        return rowView

    }
}