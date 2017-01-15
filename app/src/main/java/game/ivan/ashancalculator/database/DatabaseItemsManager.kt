package game.ivan.ashancalculator.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import game.ivan.ashancalculator.database.models.Tags

import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.models.Item

import nl.qbusict.cupboard.CupboardFactory.cupboard

/**
 * Created by ivan on 01.01.17.
 */

class DatabaseItemsManager(context: Context) {
    internal var database: SQLiteDatabase

    init {
        val dbHelper = DatabaseHelper(context)
        database = dbHelper.writableDatabase
    }

    fun insertItem(item: Item) {
        cupboard().withDatabase(database).put(item)
    }

    fun deleteItem(item: Item) {
        cupboard().withDatabase(database).delete(item)
    }

    val tagsList: List<Tags>
        get() {
            val list = cupboard().withDatabase(database).query(Tags::class.java).list()
            return list
        }

    fun deleteAll() {
        cupboard().withDatabase(database).delete(Item::class.java, null)
    }

    fun readAllRecord(): List<Item> {
        val list = cupboard().withDatabase(database).query(Item::class.java).list()
        return list
    }

    fun destroy() {
        closeConnection()
    }

    fun closeConnection() {
        database.close()
    }
}
