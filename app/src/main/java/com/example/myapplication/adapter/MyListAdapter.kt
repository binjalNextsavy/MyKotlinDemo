package com.example.myapplication.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R


class MyListAdapter(private val context: Activity,private val title: Array<String>,private val description: Array<String>) : ArrayAdapter<String>(context,
    R.layout.custom_list,title){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //val inflater = context.layoutInflater
        //val rowView = inflater.inflate(R.layout.custom_list,null,true)

        val rowView = LayoutInflater.from(context).inflate(R.layout.custom_list,null,true)
        val titleText = rowView.findViewById<TextView>(R.id.title)
        val subtitletext = rowView.findViewById<TextView>(R.id.description)

        titleText.text = title[position]
        subtitletext.text = description[position]

        return rowView
    }
}



/*
class MyAdapterForRecy(val titleList: ArrayList<User>) : RecyclerView.Adapter<MyAdapterForRecy.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterForRecy.ViewHolder {

        }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: MyAdapterForRecy.ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}*/
