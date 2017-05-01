package game.ivan.ashancalculator;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.service.RotateManager;

/**
 * Created by ivan on 24.04.2017.
 */
@Module
public class AppModule {

    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }

}
