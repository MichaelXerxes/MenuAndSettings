package com.example.menuandsettings

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity,private val id:Array<String>,
                    private val name: Array<String>, private val email: Array<String>,
                    private val password: Array<String>):ArrayAdapter<String>(context,R.layout.custom_list,name) {
    override fun getView(position: Int, view: View?, parent: ViewGroup):View {
        val inflater=context.layoutInflater
        val rowView=inflater.inflate(R.layout.custom_list,null,true)

        val idText=rowView.findViewById(R.id.viewIDNew) as TextView
        val nameText=rowView.findViewById(R.id.viewNameNew) as TextView
        val emailText=rowView.findViewById(R.id.viewEMailNew) as TextView
        val passwordText=rowView.findViewById(R.id.viewPassNew) as TextView

        idText.text="ID: ${id[position]}"
        nameText.text="Name: ${name[position]}"
        emailText.text="Email: ${email[position]}"
        passwordText.text="Password: ${password[position]}"
        return rowView
    }
}