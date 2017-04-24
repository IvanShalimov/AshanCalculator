package game.ivan.ashancalculator;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan on 24.04.2017.
 */
@Module
public class AppModule {

    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }
}
