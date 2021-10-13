package com.example.menuandsettings

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class RegistrationAdapter(var context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
         val personTable=("CREATE TABLE $TABLE_NAME"+
                 "("+ID_COLUMN+"INTEGER PRIMARY KEY,"+
                 NAME_COLUMN +"TEXT,"+
                 EMAIL_COLUMN+"TEXT,"+
                 PASS_COLUMN+"TEXT"+")")
        db?.execSQL(personTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertDATA(people:Registration):Long{
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(ID_COLUMN,people.id)
        cv.put(NAME_COLUMN,people.name)
        cv.put(EMAIL_COLUMN,people.email)
        cv.put(PASS_COLUMN,people.password)

        //println("ID- $ID_COLUMN Name- $NAME_COLUMN EM- $EMAIL_COLUMN PASS- $PASS_COLUMN  ")
        val successdDB=db.insert(TABLE_NAME, null,cv)
        return successdDB
    }
    @SuppressLint("Range")
    fun readtDATA():ArrayList<Registration>{
        val list=ArrayList<Registration>()
        val db =this.readableDatabase
        val query = ("SELECT * FROM $TABLE_NAME")
        //val result=db.rawQuery(query,null)

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
        //db.execSQL(query)
        if(cursor.moveToFirst()){
            do {

                readID=cursor.getString(cursor.getColumnIndex(ID_COLUMN)).toInt()
                readName=cursor.getString(cursor.getColumnIndex(NAME_COLUMN))
                readEmail=cursor.getString(cursor.getColumnIndex(EMAIL_COLUMN))
                readPass=cursor.getString(cursor.getColumnIndex(PASS_COLUMN))
                print("hahahahah")
                println("fddgsggsfgfssghf")
                println("$readEmail   $readName $readPass    ")
                val person= Registration(readID,readName,readEmail,readPass)

                list.add(person)



            }while (cursor.moveToNext())
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
        val TABLE_NAME="People2"
        val ID_COLUMN="ID"
        val NAME_COLUMN="Name"
        val EMAIL_COLUMN="Email"
        val PASS_COLUMN="Password"

    }


}