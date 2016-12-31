package game.ivan.ashancalculator.tags.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.database.DatabaseManager;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.view.TagsView;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsPresenter extends MvpBasePresenter<TagsView> {
    DatabaseManager databaseManager;

    public TagsPresenter(){
        databaseManager = new DatabaseManager();
    }

    public void addTag(Tags tag){
        databaseManager.insertTag(tag);
        loadTags();
    }

    public void loadTags(){
        if(isViewAttached()){
            getView().refreshList(databaseManager.readAllRecord());
            //костыль организуй Wrapper
            //databaseManager.
        }

    }

    public void deleteTag(Tags tag){
        databaseManager.delteTag(tag);
        loadTags();
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
/*        if (!retainPresenterInstance){
            cancelGreetingTaskIfRunning();
        }*/
    }
}
