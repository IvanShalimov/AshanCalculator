package game.ivan.ashancalculator.calculate.controller.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.calculate.controller.CalculateController;

/**
 * Created by ivan on 01.05.2017.
 */
@Subcomponent(modules = {CalculatorContorllerModule.class})
public interface CalculaterControllerComponent {

    void injectCalculateController(CalculateController controller);
}
