package game.ivan.ashancalculator;

import android.app.Application;

/**
 * Created by ivan on 27.12.16.
 */

public class AshanApplication extends Application {

    public static AshanApplication instante;

    public static AshanApplication getInstante(){
        if(instante == null){
            instante = new AshanApplication();
        }
        return instante;
    }

    public AshanApplication(){
        instante = this;
    }

}
