package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import android.widget.Toast
import com.example.myapplication.R

class ListviewAdapter(var names: Array<String>, var context: Context?) :
    BaseAdapter() {
    var inflter: LayoutInflater
    var value: String? = null
    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var convertView = convertView
        convertView = inflter.inflate(R.layout.checked_list_item, null)
        val simplectv = convertView.findViewById<CheckedTextView>(R.id.simplecheckedtv)
        simplectv.text = names[position]
        simplectv.setOnClickListener {
            if (simplectv.isChecked) {
                value = "un-checked"
                simplectv.setCheckMarkDrawable(R.drawable.ic_cancel_black_24dp)
                simplectv.isChecked = false
            } else {
                value = "checked"
                simplectv.setCheckMarkDrawable(R.drawable.ic_check_circle_black_24dp)
                simplectv.isChecked = true
            }
            Toast.makeText(context, "Value is $value", Toast.LENGTH_LONG).show()
        }
        return convertView
    }

    init {
        inflter = LayoutInflater.from(context)
    }
}