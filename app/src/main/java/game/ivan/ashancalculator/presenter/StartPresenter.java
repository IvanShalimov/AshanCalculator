package game.ivan.ashancalculator.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import game.ivan.ashancalculator.view.StartView;

/**
 * Created by ivan on 19.12.16.
 */

public class StartPresenter extends MvpBasePresenter<StartView> {

    public void returnText(int idAction){
        Log.d("Test","returntext");
        switch (idAction){
            case 0:
                Log.d("Test","0");
                getView().showText("addItem");
                break;
            case 1:
                Log.d("Test","1");
                getView().showText("addTag");
                break;
            case 2:
                Log.d("Test","2");
                getView().showText("clearingItems");
                break;
        }
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
/*        if (!retainPresenterInstance){
            cancelGreetingTaskIfRunning();
        }*/
    }
}
