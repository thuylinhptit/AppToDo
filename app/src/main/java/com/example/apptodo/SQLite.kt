package com.example.apptodo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf

class SQLite(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //create a empty table
    fun addToDo(list: List<CongViec>) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv= contentValuesOf()
        //enter your value
        for( i in list){
            cv.put(ITEM_CONTENT,i.muctieu)
            cv.put(FAVORITE_STATUS,i.isLike)
            cv.put(DONE_STATUS,i.isCheck)
            db.insert(TABLE_NAME,null,cv)
            db.close()
        }
    }

    // read all data
    fun readAllData(): Cursor{
        val db: SQLiteDatabase = this.readableDatabase
        val sql: String = "SELECT * FROM $TABLE_NAME"
        return  db.rawQuery(sql, null, null)
    }

    //remove line from database
    fun removeItem(id: String){
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id) )
        db.close()

    }
    //update

    fun updateIteam( id: String, congViec: CongViec ){
        val cv= ContentValues()
        val db= this.writableDatabase
        cv.put(ITEM_CONTENT,congViec.muctieu)
        cv.put(FAVORITE_STATUS, congViec.isLike)
        cv.put(DONE_STATUS, congViec.isCheck)
        db.update(TABLE_NAME,cv, "$KEY_ID = ?", arrayOf(id))
        db.close()
    }

    //select all fav list
    fun selectAllFavList(): Cursor{
        val db: SQLiteDatabase= this.readableDatabase
        val sql: String = "SELECT * FROM " + TABLE_NAME + " WHERE "+ FAVORITE_STATUS + " =true"
        return db.rawQuery(sql,null, null)
    }


}

object ListData{
    var dsHomeFragment: MutableList<CongViec> = mutableListOf()
    var dsFavFragment: MutableList<CongViec> = mutableListOf()
    var dsDoneFragment: MutableList<CongViec> = mutableListOf()
    lateinit var db: SQLite
}