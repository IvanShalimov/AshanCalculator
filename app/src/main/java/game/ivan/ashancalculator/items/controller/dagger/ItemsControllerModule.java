package game.ivan.ashancalculator.items.controller.dagger;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.items.controller.ItemListAdapter;

/**
 * Created by ivan on 01.05.2017.
 */
@Module
public class ItemsControllerModule {

    @Provides
    public ItemListAdapter provideItemListAdapter(){
        return new ItemListAdapter();
    }
}
