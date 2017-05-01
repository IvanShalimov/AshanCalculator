package game.ivan.ashancalculator;

import javax.inject.Singleton;

import dagger.Component;
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
}
