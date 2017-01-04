package game.ivan.ashancalculator.calculate.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import game.ivan.ashancalculator.calculate.view.CalculaterView;
import game.ivan.ashancalculator.database.DatabaseCalculateManager;
import game.ivan.ashancalculator.database.models.Tags;

/**
 * Created by ivan on 03.01.17.
 */

public class CalculatedPresenter extends MvpBasePresenter<CalculaterView> {

    DatabaseCalculateManager databaseManager;
    public CalculatedPresenter(){
        databaseManager = new DatabaseCalculateManager();
    }

    public void getTags(){
        List<String> labels = new ArrayList<>();
        for (Tags tag:databaseManager.readAllTags()){
            labels.add(tag.nameTags);
        }
        if (isViewAttached())
            getView().setSpinnerData(labels);
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }

    public void getDateForScreen(int position){

    }
}
