package game.ivan.ashancalculator.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.StringJoiner

import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.models.Item
import game.ivan.ashancalculator.database.models.Tags

import nl.qbusict.cupboard.CupboardFactory.cupboard

/**
 * Created by ivan on 03.01.17.
 */

class DatabaseCalculateManager(context: Context) {
    internal var database: SQLiteDatabase

    init {
        val dbHelper = DatabaseHelper(context)
        database = dbHelper.writableDatabase
    }

    fun readAllTags(): List<Tags> {
        val list = cupboard().withDatabase(database).query(Tags::class.java).list()
        return list
    }

    fun getItemForTag(tagId: Int): List<Item> {
        val items = cupboard()
                .withDatabase(database).query(Item::class.java)
                .withSelection("tag_id = ?", tagId.toString())
                .list()
        return items
    }

    fun getDivider(tagId: Int): Int {
        val tag = cupboard().withDatabase(database)
                .query(Tags::class.java)
                .withSelection("_id = ?", tagId.toString())
                .get()

        return tag.divisionFactor
    }

    fun destroy() {
        closeConnection()
    }

    fun closeConnection() {
        database.close()
    }
}
