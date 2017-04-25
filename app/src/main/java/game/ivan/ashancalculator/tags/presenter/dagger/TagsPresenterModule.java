package game.ivan.ashancalculator.tags.presenter.dagger;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.database.DatabaseTagsManager;

/**
 * Created by ivan on 25.04.2017.
 */

@Module
public class TagsPresenterModule {

    @Provides
    DatabaseTagsManager providesDatabaseTagsManager(){
        return  new DatabaseTagsManager();
    }
}
