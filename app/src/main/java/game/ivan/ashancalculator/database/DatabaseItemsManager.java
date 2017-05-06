package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import game.ivan.ashancalculator.database.models.Tags;

import java.util.List;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.models.Item;
import io.reactivex.Observable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by ivan on 01.01.17.
 */

public class DatabaseItemsManager  {
    SQLiteDatabase database;

    public SQLiteDatabase openConnection(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public void insertItem(Item item){
        openConnection(AshanApplication.getInstance());
        cupboard().withDatabase(database).put(item);
        closeConnection();
    }

    public void deleteItem(Item item){
        openConnection(AshanApplication.getInstance());
        cupboard().withDatabase(database).delete(item);
        closeConnection();
    }

    public Observable<List<Tags>> getTagsList(){
        openConnection(AshanApplication.getInstance());
        List<Tags> list = cupboard().withDatabase(database).query(Tags.class).list();
        closeConnection();
        return Observable.just(list);
    }

    public void deleteAll(){
        openConnection(AshanApplication.getInstance());
        cupboard().withDatabase(database).delete(Item.class,null);
        closeConnection();
    }

    public Observable<List<Item>> readAllRecord(){
        openConnection(AshanApplication.getInstance());
        List<Item> list = cupboard().withDatabase(database).query(Item.class).list();
        closeConnection();
        return Observable.just(list);
    }

    public void closeConnection(){
        database.close();
    }
}
