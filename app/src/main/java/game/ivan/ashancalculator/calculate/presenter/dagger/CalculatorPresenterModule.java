package game.ivan.ashancalculator.calculate.presenter.dagger;

import dagger.Module;
import dagger.Provides;
import game.ivan.ashancalculator.database.DatabaseCalculateManager;
import game.ivan.ashancalculator.service.Calculator;

/**
 * Created by ivan on 01.05.2017.
 */
@Module
public class CalculatorPresenterModule {

    @Provides
    public Calculator provideCalculator(){
        return new Calculator();
    }

    @Provides
    public DatabaseCalculateManager provideDatabaseCalculateManager(){
        return new DatabaseCalculateManager();
    }
}
