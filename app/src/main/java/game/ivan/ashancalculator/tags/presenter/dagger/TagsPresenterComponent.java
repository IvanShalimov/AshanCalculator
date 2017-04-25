package game.ivan.ashancalculator.tags.presenter.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.tags.presenter.TagsPresenter;

/**
 * Created by ivan on 25.04.2017.
 */
@Subcomponent(modules = {TagsPresenterModule.class})
public interface TagsPresenterComponent {

    void injectTagsPresenter(TagsPresenter tagsPresenter);
}
