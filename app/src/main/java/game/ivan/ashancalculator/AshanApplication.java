package game.ivan.ashancalculator;

import android.app.Application;


/**
 * Created by ivan on 27.12.16.
 */

public class AshanApplication extends Application {

    public static AshanApplication instance;
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component =  DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                build();
        component.injectApp(this);
    }

    public static AshanApplication getInstance(){
        if(instance == null){
            instance = new AshanApplication();
        }
        return instance;
    }

    public AshanApplication(){
        instance = this;

    }

    public static AppComponent getComponent(){
        return component;
    }

}
