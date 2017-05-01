package game.ivan.ashancalculator.items.presenter.dagger;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.database.DatabaseItemsManager;
import game.ivan.ashancalculator.service.RotateManager;

/**
 * Created by ivan on 01.05.2017.
 */

@Module
public class ItemsPresenterModule {

    @Provides
    public RotateManager provideRotateManager(){
        return  new RotateManager();
    }

    @Provides
    public DatabaseItemsManager provideDatabaseItemsManager(){
        return new DatabaseItemsManager();
    }
}
