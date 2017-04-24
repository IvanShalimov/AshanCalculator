package game.ivan.ashancalculator;

import android.app.Application;

/**
 * Created by ivan on 27.12.16.
 */

public class AshanApplication extends Application {

    public static AshanApplication instante;
    private  AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component =  DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                build();
        component.injectApp(this);
    }

    public static AshanApplication getInstante(){
        if(instante == null){
            instante = new AshanApplication();
        }
        return instante;
    }

    public AshanApplication(){
        instante = this;

    }

    public AppComponent getConmonent(){
        return component;
    }

}
