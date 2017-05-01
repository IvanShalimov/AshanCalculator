package game.ivan.ashancalculator;

import javax.inject.Singleton;

import dagger.Component;
import game.ivan.ashancalculator.calculate.controller.dagger.CalculaterControllerComponent;
import game.ivan.ashancalculator.calculate.presenter.dagger.CalculaterPresenterComponent;
import game.ivan.ashancalculator.items.controller.dagger.ItemsControllerComponent;
import game.ivan.ashancalculator.items.presenter.dagger.ItemsPresenterComponent;
import game.ivan.ashancalculator.tags.controller.dagger.TagControllerComponent;
import game.ivan.ashancalculator.tags.presenter.dagger.TagsPresenterComponent;

/**
 * Created by ivan on 24.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectApp(AshanApplication application);

    TagControllerComponent createTagControllerComponent();

    TagsPresenterComponent createTagsPresenterComponent();

    ItemsPresenterComponent createItemsPresenterComponent();

    ItemsControllerComponent createItemsConrtollerComponent();

    CalculaterPresenterComponent createCalculaterPresenterComponent();

    CalculaterControllerComponent createCalculaterControllerComponent();
}
