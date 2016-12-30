package game.ivan.ashancalculator.start.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.start.view.StartView;

/**
 * Created by ivan on 19.12.16.
 */

public class StartPresenter extends MvpBasePresenter<StartView> {

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }
}
