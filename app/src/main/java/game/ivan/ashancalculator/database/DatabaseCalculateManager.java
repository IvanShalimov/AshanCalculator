package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;
import java.util.StringJoiner;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;
import io.reactivex.Observable;

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

    public Observable<List<Tags>> readAllTags(){
        openConnection(AshanApplication.getInstante());
        List<Tags> list = cupboard().withDatabase(database).query(Tags.class).list();
        closeConnection();
        return Observable.just(list);
    }

    public Observable<List<Item>> getItemForTag(int tagId){
        openConnection(AshanApplication.getInstante());
        List<Item> items = cupboard()
                .withDatabase(database).query(Item.class)
                .withSelection("tag_id = ?", String.valueOf(tagId))
                .list();
        closeConnection();
        return Observable.just(items);
    }

    public int getDivider(int tagId){
        openConnection(AshanApplication.getInstante());
        Tags tag = cupboard().withDatabase(database)
                .query(Tags.class)
                .withSelection("_id = ?", String.valueOf(tagId))
                .get();
        closeConnection();

        return tag.divisionFactor;
    }

    public void closeConnection(){
        database.close();
    }
}
