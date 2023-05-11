package com.djatscode.crudsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1

        // database name
        private const val DATABASE_NAME =  "DB_APP"

        // table name
        private const val TABLE_USER = "USER"

        // column tables
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE_NUM = "phone_num"
        private const val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val querCreateTable = ("CREATE TABLE" + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + "TEXT,"
                + KEY_PHONE_NUM + " TEXT" + KEY_EMAIL + " TEXT")
        db?.execSQL(querCreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    fun insertUser(userModels: User) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, userModels.name)
        values.put(KEY_PHONE_NUM, userModels.phoneNum)
        values.put(KEY_EMAIL, userModels.email)
        db.insert(TABLE_USER, null, values)
        db.close()
    }

}


