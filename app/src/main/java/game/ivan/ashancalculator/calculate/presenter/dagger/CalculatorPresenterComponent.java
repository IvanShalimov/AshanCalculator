package game.ivan.ashancalculator.calculate.presenter.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.calculate.presenter.CalculatedPresenter;

/**
 * Created by ivan on 01.05.2017.
 */
@Subcomponent(modules = {CalculatorPresenterModule.class})
public interface CalculatorPresenterComponent {

    void injectCalculatedPresenter(CalculatedPresenter presenter);
}
