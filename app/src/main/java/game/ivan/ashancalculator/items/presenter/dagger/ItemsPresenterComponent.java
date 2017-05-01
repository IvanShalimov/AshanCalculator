package game.ivan.ashancalculator.items.presenter.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.items.presenter.ItemsPresenter;

/**
 * Created by ivan on 01.05.2017.
 */

@Subcomponent(modules = {ItemsPresenterModule.class})
public interface ItemsPresenterComponent {

    void injectItemsPresenter(ItemsPresenter presenter);
}
