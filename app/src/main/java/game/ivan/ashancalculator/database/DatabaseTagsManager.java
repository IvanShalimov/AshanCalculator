package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.models.Tags;
import io.reactivex.Observable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by ivan on 30.12.16.
 */

public class DatabaseTagsManager {
    private SQLiteDatabase database;

    private SQLiteDatabase openConnection(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public void insertTag(Tags tag){
        openConnection(AshanApplication.getInstance());
        cupboard().withDatabase(database).put(tag);
        closeConnection();
    }

    public void deleteTag(Tags tag){
        openConnection(AshanApplication.getInstance());
        cupboard().withDatabase(database).delete(tag);
        closeConnection();
    }

    public Observable<List<Tags>> readAllRecord(){
        openConnection(AshanApplication.getInstance());
        List<Tags> list = cupboard().withDatabase(database).query(Tags.class).list();
        closeConnection();
        return Observable.just(list);
    }

    private void closeConnection(){
        database.close();
    }
}
