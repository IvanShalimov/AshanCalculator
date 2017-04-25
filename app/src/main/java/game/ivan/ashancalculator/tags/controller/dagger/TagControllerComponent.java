package game.ivan.ashancalculator.tags.controller.dagger;

import dagger.Subcomponent;
import game.ivan.ashancalculator.tags.controller.TagsController;

/**
 * Created by ivan on 25.04.2017.
 */
@Subcomponent(modules = {TagControllerModule.class})
public interface TagControllerComponent {

    void inhjectTagController(TagsController controller);
}
