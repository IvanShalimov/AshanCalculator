package game.ivan.ashancalculator.calculate.controller.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.calculate.controller.CalculateController;

/**
 * Created by ivan on 01.05.2017.
 */
@Subcomponent(modules = {CalculatorControllerModule.class})
public interface CalculatorControllerComponent {

    void injectCalculateController(CalculateController controller);
}
