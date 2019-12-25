package com.example.toastylibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class FancyToast(context: Context) : Toast(context) {


   companion object{

    var success = 1
    var warning = 2
    var error  = 3
    var info = 4
    var default = 5
    var confusing = 6

       public fun makeText(context: Context?, message: String?, duration: Int, type: Int, androidIcon: Boolean): Toast? {
           val toast = Toast(context)
           toast.duration = duration

           val layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false)
           val l1 = layout.findViewById<View>(R.id.toast_text) as TextView
           val linearLayout = layout.findViewById<View>(R.id.toast_type) as LinearLayout

           val img = layout.findViewById<View>(R.id.toast_icon) as ImageView
           val img1 = layout.findViewById<View>(R.id.imageView4) as ImageView
           l1.text = message

           if (androidIcon) img1.visibility = View.VISIBLE else if (!androidIcon) img1.visibility = View.GONE
           when (type) {
               1 -> {
                   linearLayout.setBackgroundResource(R.drawable.success_shape)
                   img.setImageResource(R.drawable.ic_check_black_24dp)
               }

               2 -> {
                   linearLayout.setBackgroundResource(R.drawable.warning_shape)
                   img.setImageResource(R.drawable.ic_pan_tool_black24dp)
               }

               3 -> {
                   linearLayout.setBackgroundResource(R.drawable.error_shape)
                   img.setImageResource(R.drawable.ic_black_clear_24dp)
               }

               4 -> {
                   linearLayout.setBackgroundResource(R.drawable.info_shape)
                   img.setImageResource(R.drawable.ic_info_outline_black_24dp)
               }

               5 -> {
                   linearLayout.setBackgroundResource(R.drawable.default_shape)
                   img.visibility = View.GONE
               }

               6 -> {
                   linearLayout.setBackgroundResource(R.drawable.confusing_shape)
                   img.setImageResource(R.drawable.ic_refresh_black_24dp)
               }
           }
           toast.view = layout
           return toast
       }

       public fun makeText(context: Context?, message: String?, duration: Int, type: Int, ImageResource: Int, androidIcon: Boolean): Toast? {
           val toast = Toast(context)
           val layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, null, false)
           val l1 = layout.findViewById<View>(R.id.toast_text) as TextView

           val linearLayout = layout.findViewById<View>(R.id.toast_type) as LinearLayout
           val img = layout.findViewById<View>(R.id.toast_icon) as ImageView
           val img1 = layout.findViewById<View>(R.id.imageView4) as ImageView

           l1.text = message
           img.setImageResource(ImageResource)

           if (androidIcon) img1.visibility = View.VISIBLE else if (!androidIcon) img1.visibility = View.GONE
           when (type) {
               1 -> linearLayout.setBackgroundResource(R.drawable.success_shape)
               2 -> linearLayout.setBackgroundResource(R.drawable.warning_shape)
               3 -> linearLayout.setBackgroundResource(R.drawable.error_shape)
               4 -> linearLayout.setBackgroundResource(R.drawable.info_shape)
               5 -> {
                   linearLayout.setBackgroundResource(R.drawable.default_shape)
                   img.visibility = View.GONE
               }
               6 -> linearLayout.setBackgroundResource(R.drawable.confusing_shape)
               else -> {
                   linearLayout.setBackgroundResource(R.drawable.default_shape)
                   img.visibility = View.GONE
               }
           }
           toast.view = layout
           return toast
       }
       }


}