package com.example.menuandsettings

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity,private val id:Array<String>,
                    private val name: Array<String>, private val email: Array<String>,
                    private val password: Array<String>):ArrayAdapter<String>(context,R.layout.listview2) {
    override fun getView(position: Int, view: View?, parent: ViewGroup):View {
        val inflater=context.layoutInflater
        val rowView=inflater.inflate(R.layout.listview2,null,true)

        val idText=rowView.findViewById(R.id.viewID2) as TextView
        val nameText=rowView.findViewById(R.id.viewName2) as TextView
        val emailText=rowView.findViewById(R.id.viewEmail2) as TextView
        val passwordText=rowView.findViewById(R.id.viewPass2) as TextView

        idText.text="ID: ${id[position]}"
        nameText.text="Name: ${name[position]}"
        emailText.text="Email: ${email[position]}"
        passwordText.text="Password: ${password[position]}"
        return rowView
    }
}