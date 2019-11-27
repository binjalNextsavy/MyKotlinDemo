package com.example.myapplication.sqlitdemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context : Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "userTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + ")")
        //val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + ")")

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS)

        onCreate(db)
    }

    //Add User
    fun addUser(objUser: UserRegisterData): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ID,objUser.userId)
        contentValues.put(KEY_NAME,objUser.userName)
        contentValues.put(KEY_EMAIL,objUser.userEmail)

        val success = db.insert(TABLE_CONTACTS,null,contentValues)
        db.close()

        return success
    }

    //Read user
    fun viewuser() : List<UserRegisterData>{
        val userList: ArrayList<UserRegisterData> = ArrayList()

        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase

        var cursor: Cursor ? =null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userId: Int
        var userName: String
        var userEmail: String

        if(cursor.moveToFirst()){
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                userName = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                userEmail = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))

                val user = UserRegisterData(userId = userId,userName = userName,userEmail = userEmail)
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    //Update data
    fun updateUser(user: UserRegisterData) : Int {
        val db = this.writableDatabase
        val contentValues: ContentValues = ContentValues()

        contentValues.put(KEY_ID,user.userId)
        contentValues.put(KEY_NAME,user.userName)
        contentValues.put(KEY_EMAIL,user.userEmail)

        val success = db.update(TABLE_CONTACTS,contentValues,"id =" +user.userId,null)
        db.close()
        return success
    }

    //delete data
    fun deletreUser(user: UserRegisterData) : Int{
        val db = this.writableDatabase
        /*val contentValues = ContentValues()

        contentValues.put(KEY_ID,user.userId)
*/
        val success = db.delete(TABLE_CONTACTS,"id= "+user.userId, null)
        db.close()
        return success
    }


}