package com.example.menuandsettings

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder
val DATABASE_NAME="MDB"
val TABLE_NAME="People"
val NAME_COLUMN="Name"
val EMAIL_COLUMN="Email"
val PASS_COLUMN="Password"
class RegistrationAdapter(var context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
         val personTable="CREATE TABLE"+TABLE_NAME+" ("+
                 NAME_COLUMN+" VARCHAR(256),"+
                 EMAIL_COLUMN+" VARCHAR(256),"+
                 PASS_COLUMN+" VARCHAR(256))"
        p0!!.execSQL(personTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertDATA(people:Registration){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(NAME_COLUMN,people.name)
        cv.put(EMAIL_COLUMN,people.email)
        cv.put(PASS_COLUMN,people.password)
        db.insert(TABLE_NAME, null,cv)
    }
    fun readtDATA():List<Registration>{
        val list=ArrayList<Registration>()
        val db =this.readableDatabase
        val query = "SELECT*FROM"+TABLE_NAME
        val result=db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val person=Registration()
                person.name=result.getString(result.getColumnIndex(NAME_COLUMN).toInt())
                person.email=result.getString(result.getColumnIndex(EMAIL_COLUMN).toInt())
                person.password=result.getString(result.getColumnIndex(PASS_COLUMN).toInt())
                list.add(person)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }


}