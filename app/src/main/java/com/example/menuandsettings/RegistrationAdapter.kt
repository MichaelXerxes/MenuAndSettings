package com.example.menuandsettings

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder
val DATABASE_NAME="MDB"
val TABLE_NAME="People"
val ID_COLUMN="ID"
val NAME_COLUMN="Name"
val EMAIL_COLUMN="Email"
val PASS_COLUMN="Password"
class RegistrationAdapter(var context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
         val personTable="CREATE TABLE"+TABLE_NAME+
                 "("+ID_COLUMN+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                 NAME_COLUMN +"TEXT,"+
                 EMAIL_COLUMN+"TEXT,"+
                 PASS_COLUMN+"TEXT"+")"
        p0?.execSQL(personTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertDATA(people:Registration){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(ID_COLUMN,people.id)
        cv.put(NAME_COLUMN,people.name)
        cv.put(EMAIL_COLUMN,people.email)
        cv.put(PASS_COLUMN,people.password)
        db.insert(TABLE_NAME, null,cv)
    }
    @SuppressLint("Range")
    fun readtDATA():List<Registration>{
        val list=ArrayList<Registration>()
        val db =this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result=db.rawQuery(query,null)

        var cursor:Cursor?=null
        var readID:Int
        var readName:String
        var readEmail:String
        var readPass:String
        try {
            cursor=db.rawQuery(query,null)
        }catch (e: SQLiteException){
            db.execSQL(query)
            return list
        }
        db.execSQL(query)
        if(result.moveToFirst()){
            do {

                val person:Registration

                readID=result.getString(result.getColumnIndex(ID_COLUMN)).toInt()
                readName=result.getString(result.getColumnIndex(NAME_COLUMN))
                readEmail=result.getString(result.getColumnIndex(EMAIL_COLUMN))
                readPass=result.getString(result.getColumnIndex(PASS_COLUMN))
                person= Registration(readID,readName,readEmail,readPass)
                list.add(person)



            }while (result.moveToNext())
        }
       /* if(result.moveToFirst()){
            do {
                val person=Registration()
                person.id=result.toString().toInt()
                person.name=result.toString()
                person.email=result.toString()
                person.password=result.toString()
                //person.id=result.getString(result.getColumnIndex(ID_COLUMN).toInt()).toInt()
                //person.name=result.getString(result.getColumnIndex(NAME_COLUMN).toInt())
                //person.email=result.getString(result.getColumnIndex(EMAIL_COLUMN).toInt())
               // person.password=result.getString(result.getColumnIndex(PASS_COLUMN).toInt())
                list.add(person)
            }while (result.moveToNext())
        } */

       // result.close()
       // db.close()
        return list
    }
    companion object{
        val DATABASE_NAME="MDB"
        val TABLE_NAME="People"
        val ID_COLUMN="ID"
        val NAME_COLUMN="Name"
        val EMAIL_COLUMN="Email"
        val PASS_COLUMN="Password"

    }


}