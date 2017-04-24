package game.ivan.ashancalculator;

import dagger.Component;

/**
 * Created by ivan on 24.04.2017.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectApp(AshanApplication application);
}
