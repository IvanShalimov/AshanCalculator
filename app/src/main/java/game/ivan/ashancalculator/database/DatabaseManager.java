package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.models.Tags;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by ivan on 30.12.16.
 */

public class DatabaseManager {
    SQLiteDatabase database;

    public SQLiteDatabase openConnection(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public void insertTag(Tags tag){
        openConnection(AshanApplication.getInstante());
        cupboard().withDatabase(database).put(tag);
        closeConnection();
    }

    public void delteTag(Tags tag){
        openConnection(AshanApplication.getInstante());
        cupboard().withDatabase(database).delete(tag);
        closeConnection();
    }

    public List<Tags> readAllRecord(){
        openConnection(AshanApplication.getInstante());
        List<Tags> list = cupboard().withDatabase(database).query(Tags.class).list();
        closeConnection();
        return list;
    }

    public void closeConnection(){
        database.close();
    }
}
