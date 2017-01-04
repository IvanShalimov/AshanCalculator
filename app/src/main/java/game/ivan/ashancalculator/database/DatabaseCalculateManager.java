package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.models.Tags;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by ivan on 03.01.17.
 */

public class DatabaseCalculateManager {
    SQLiteDatabase database;

    public SQLiteDatabase openConnection(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public List<Tags> readAllTags(){
        openConnection(AshanApplication.getInstante());
        List<Tags> list = cupboard().withDatabase(database).query(Tags.class).list();
        closeConnection();
        return list;
    }

    public void closeConnection(){
        database.close();
    }
}
