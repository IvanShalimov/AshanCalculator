package game.ivan.ashancalculator.repository;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by ivan on 06.05.2017.
 */

public class PreferencesRepository implements Repository {
    Context context;
    private static final String modeKey = "add_item_mode";

    public PreferencesRepository(Context context){
        this.context = context;
    }

    @Override
    public boolean checkAddMode() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(modeKey,false);
    }
}
