package game.ivan.ashancalculator.items.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.items.view.ItemsView;

/**
 * Created by ivan on 21.12.16.
 */

public class ItemsPresenter extends MvpBasePresenter<ItemsView> {

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
/*        if (!retainPresenterInstance){
            cancelGreetingTaskIfRunning();
        }*/
    }
}
