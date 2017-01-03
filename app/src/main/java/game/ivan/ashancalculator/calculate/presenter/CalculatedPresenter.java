package game.ivan.ashancalculator.calculate.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.calculate.view.CalculaterView;
import game.ivan.ashancalculator.database.DatabaseCalculateManager;

/**
 * Created by ivan on 03.01.17.
 */

public class CalculatedPresenter extends MvpBasePresenter<CalculaterView> {

    DatabaseCalculateManager databaseManager;
    public CalculatedPresenter(){
        databaseManager = new DatabaseCalculateManager();
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }
}
