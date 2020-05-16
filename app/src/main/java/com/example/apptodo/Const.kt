package com.example.apptodo

import android.os.Build

const val DATABASE_NAME="ToDoList.db"
const val DB_VERSION=1
const val TABLE_NAME="ToDo"
const val KEY_ID="id"
const val ITEM_CONTENT="content"
const val FAVORITE_STATUS="fav"
const val DONE_STATUS="done"
const val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"(" + KEY_ID +" TEXT, " + ITEM_CONTENT +" TEXT, " + FAVORITE_STATUS + " TEXT, " + DONE_STATUS+ " TEXT)"



