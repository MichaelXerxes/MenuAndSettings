package com.example.menuandsettings

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.StringBuilder

class RegistrationAdapter(var context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,6) {
    override fun onCreate(db: SQLiteDatabase?) {
         val personTable=("CREATE TABLE "+ TABLE_NAME +
                 "("+ ID_COLUMN +" INTEGER PRIMARY KEY, "+
                 NAME_COLUMN +" TEXT, "+
                 EMAIL_COLUMN +" TEXT, "+
                 PASS_COLUMN +" TEXT)")

        db!!.execSQL(personTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertDATA(people:Registration):Long{
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(ID_COLUMN,people.id)
        cv.put(NAME_COLUMN,people.name)
        cv.put(EMAIL_COLUMN,people.email)
        cv.put(PASS_COLUMN,people.password)

        Log.println(Log.VERBOSE,"Adapter","ID- $ID_COLUMN Name- $NAME_COLUMN EM- $EMAIL_COLUMN PASS- $PASS_COLUMN  ")
        Log.println(Log.VERBOSE,"Adapter fromReg con","ID ${people.id} Name ${people.name} Email ${people.email} Pass ${people.password}")
        val successdDB=db.insert(TABLE_NAME, null,cv)
        db.close()
        return successdDB
    }
    @SuppressLint("Range")
    fun readtDATA():ArrayList<Registration>{
        val list=ArrayList<Registration>()
        val db =this.readableDatabase
        val query = ("SELECT  * FROM $TABLE_NAME")

        var cursor:Cursor?=null
        var readID:Int
        var readName:String
        var readEmail:String
        var readPass:String
        try {
            cursor=db.rawQuery(query,null)
        }catch (e: SQLiteException){
            db.execSQL(query)
            return ArrayList()
        }
        if(cursor.moveToFirst()){
            do {

                readID=cursor.getInt(cursor.getColumnIndex("ID_COLUMN"))
                readName=cursor.getString(cursor.getColumnIndex("NAME_COLUMN"))
                readEmail=cursor.getString(cursor.getColumnIndex("EMAIL_COLUMN"))
                readPass=cursor.getString(cursor.getColumnIndex("PASS_COLUMN"))
                val person= Registration(readID,readName,readEmail,readPass)
                list.add(person)

            }while (cursor.moveToNext())
        }


       // result.close()
       // db.close()
        return list
    }
    companion object{
        val DATABASE_NAME="7"
        val TABLE_NAME="People2"
        val ID_COLUMN="ID_COLUMN"
        val NAME_COLUMN="NAME_COLUMN"
        val EMAIL_COLUMN="EMAIL_COLUMN"
        val PASS_COLUMN="PASS_COLUMN"

    }


}