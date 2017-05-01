package game.ivan.ashancalculator.items.controller.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.items.controller.ItemsController;

/**
 * Created by ivan on 01.05.2017.
 */

@Subcomponent(modules = {ItemsControllerModule.class})
public interface ItemsControllerComponent {

    void injectItemsController(ItemsController controller);
}
