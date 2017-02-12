package game.ivan.ashancalculator.tags.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.database.DatabaseTagsManager;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.view.TagsView;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsPresenter extends MvpBasePresenter<TagsView> {
    private DatabaseTagsManager databaseManager;

    public TagsPresenter(){
        databaseManager = new DatabaseTagsManager();
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
}
