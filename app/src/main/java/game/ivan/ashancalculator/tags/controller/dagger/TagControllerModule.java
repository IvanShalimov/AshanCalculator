package game.ivan.ashancalculator.tags.controller.dagger;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.tags.controller.TagsListAdapter;

/**
 * Created by ivan on 25.04.2017.
 */
@Module
public class TagControllerModule {

    @Provides
    RecyclerView.LayoutManager provideLinearLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }

    @Provides
    TagsListAdapter providesTagsListAdapter(){
        return new TagsListAdapter();
    }
}
