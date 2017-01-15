package game.ivan.ashancalculator.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import game.ivan.ashancalculator.database.models.Item
import game.ivan.ashancalculator.database.models.Tags

import nl.qbusict.cupboard.CupboardFactory.cupboard

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables()
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables()
    }

    companion object {

        private val DATABASE_NAME = "ashancalculatordatabase.db"
        private val DATABASE_VERSION = 1

        init {
            cupboard().register(Tags::class.java)
            cupboard().register(Item::class.java)
        }
    }
}
