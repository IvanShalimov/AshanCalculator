package game.ivan.ashancalculator.tags.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.tags.view.TagsView;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsPresenter extends MvpBasePresenter<TagsView> {

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
/*        if (!retainPresenterInstance){
            cancelGreetingTaskIfRunning();
        }*/
    }
}
