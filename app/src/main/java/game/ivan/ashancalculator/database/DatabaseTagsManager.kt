package game.ivan.ashancalculator.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.models.Tags

import nl.qbusict.cupboard.CupboardFactory.cupboard

/**
 * Created by ivan on 30.12.16.
 */

class DatabaseTagsManager(context: Context) {

    internal var database: SQLiteDatabase

    init {
        val dbHelper = DatabaseHelper(context)
        database = dbHelper.writableDatabase
    }

    fun insertTag(tag: Tags) {
        cupboard().withDatabase(database).put(tag)
    }

    fun delteTag(tag: Tags) {
        cupboard().withDatabase(database).delete(tag)
    }

    fun readAllRecord(): List<Tags> {
        val list = cupboard().withDatabase(database).query(Tags::class.java).list()
        return list
    }

    fun destroy(){
        closeConnection()
    }
    fun closeConnection() {
        database.close()
    }
}
