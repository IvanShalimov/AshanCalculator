package game.ivan.ashancalculator.calculate.controller.dagger;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.calculate.controller.CalculatedListItemAdapter;

/**
 * Created by ivan on 01.05.2017.
 */
@Module
public class CalculatorControllerModule {

    @Provides
    public CalculatedListItemAdapter provideCalculatedListItemAdapter(){
        return new CalculatedListItemAdapter();
    }
}
