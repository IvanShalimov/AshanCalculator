package game.ivan.ashancalculator.tags.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.DatabaseTagsManager;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.presenter.dagger.TagsPresenterComponent;
import game.ivan.ashancalculator.tags.view.TagsView;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsPresenter extends MvpBasePresenter<TagsView> {

    public static final int DEFAULT_DIVISION_VALUE = 1;

    @Inject
    DatabaseTagsManager databaseManager;
    TagsPresenterComponent component;


    public TagsPresenter(){
        component = AshanApplication.getComponent().createTagsPresenterComponent();
        component.injectTagsPresenter(this);
    }

    public void addTag(Tags tag){
        databaseManager.insertTag(tag);
        loadTags(false);
    }

    public void loadTags(boolean lock){
        if(!lock && isViewAttached()){
            getView().refreshList(databaseManager.readAllRecord());
        }
    }

    public void deleteTag(Tags tag){
        databaseManager.deleteTag(tag);
        loadTags(false);
    }

    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }

    public int checkDivision(String division){
        try {
            return Integer.valueOf(division);
        } catch(NumberFormatException | NullPointerException e){
            return DEFAULT_DIVISION_VALUE;
        }

    }

}
