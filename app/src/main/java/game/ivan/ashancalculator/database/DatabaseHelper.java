package game.ivan.ashancalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by ivan on 21.12.16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ashancalculatordatabase.db";
    private static final int DATABASE_VERSION = 1;

    static {
        // register our models
        cupboard().register(Tags.class);
        cupboard().register(Item.class);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        cupboard().withDatabase(sqLiteDatabase).upgradeTables();
    }
}
