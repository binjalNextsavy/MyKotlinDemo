package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User

/*class AdapterForrecycler (val userList : ArrayList<User>) : RecyclerView.Adapter<AdapterForrecycler.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindItems(userList[position])

        var data = userList[position]
        holder.textUser!!.text = data.mName
        holder.textAddr!!.text = data.mAddress
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

    //    fun bindItems(user: User){
            var textUser: TextView? = itemView.findViewById<TextView>(R.id.textViewUsername)
            var textAddr: TextView? = itemView.findViewById<TextView>(R.id.textViewAddress)

      *//*      textUser.text = user.name
            textAddr.text = user.address
        }
*//*
    }

}*/

class AdapterForrecycler1 (val userList : ArrayList<String>) : RecyclerView.Adapter<AdapterForrecycler1.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindItems(userList[position])

        var data = userList[position]
        holder.textUser!!.text = data
        //holder.textAddr!!.text = data.mAddress
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){

        //    fun bindItems(user: User){
        var textUser: TextView? = itemView.findViewById<TextView>(R.id.textViewUsername)
        //var textAddr: TextView? = itemView.findViewById<TextView>(R.id.textViewAddress)

        /*      textUser.text = user.name
              textAddr.text = user.address
          }
  */
    }

}

