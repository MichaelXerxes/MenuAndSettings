package com.example.menuandsettings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity:AppCompatActivity() {
    lateinit var imageviewFromLayout: View
    lateinit var profilepictire: ImageView
    lateinit var havebutton:ImageView
    lateinit var regbutton:Button
    lateinit var view1:View
    var selectedPhoto:Uri?=null

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
      setContentView(R.layout.registerlayout)

        view1=layoutInflater.inflate(R.layout.registerlayout,null)
        imageviewFromLayout=view1.findViewById(R.id.imageView1)
        regbutton=view1.findViewById(R.id.register_button)
        havebutton=view1.findViewById(R.id.alreadyhave)
        profilepictire=view1.findViewById(R.id.imageView1)


        imageviewFromLayout.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,0)
        }
        regbutton.setOnClickListener {
            register()
        }
        havebutton.setOnClickListener {
            Toast.makeText(this@RegisterActivity,"Have Button working Toast",Toast.LENGTH_SHORT).show()

            ///Login Activity here !!!!!!!!!!!!!!!
        }

   }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode==Activity.RESULT_OK && data!=null){
            selectedPhoto=data.data

            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,selectedPhoto)
            profilepictire.setImageBitmap(bitmap)

        }
    }

    fun register(){
        var name=view1.findViewById<EditText>(R.id.editname)
        var email=view1.findViewById<EditText>(R.id.editemail)
        var pass=view1.findViewById<EditText>(R.id.editpasword)

        name.text.toString()
        email.text.toString()
        pass.text.toString()
        if (profilepictire==null){
            Toast.makeText(this@RegisterActivity,"Please select a picture",Toast.LENGTH_SHORT).show()
            return
        }
        if(name==null){
            Toast.makeText(this@RegisterActivity,"Please enter name",Toast.LENGTH_SHORT).show()
            return
        }
        if(email==null){
            Toast.makeText(this@RegisterActivity,"Please enter email",Toast.LENGTH_SHORT).show()
            return
        }
        if(pass==null){
            Toast.makeText(this@RegisterActivity,"Please enter password",Toast.LENGTH_SHORT).show()
            return
        }
   }
}