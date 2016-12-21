package game.ivan.ashancalculator.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by ivan on 21.12.16.
 */
class DatabaseManager{
    private lateinit var db:SQLiteDatabase

    fun openConnection(context: Context): SQLiteDatabase{
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.getWritableDatabase()
        return db
    }

    fun closeConnection(){
        db.close()
    }
}
