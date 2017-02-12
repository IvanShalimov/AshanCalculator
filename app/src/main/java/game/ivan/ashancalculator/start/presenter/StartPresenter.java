package game.ivan.ashancalculator.start.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import game.ivan.ashancalculator.database.DatabaseTagsManager;
import game.ivan.ashancalculator.start.view.StartView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivan on 19.12.16.
 */

public class StartPresenter extends MvpBasePresenter<StartView> {

    private DatabaseTagsManager databaseManager;

    public StartPresenter(){
        databaseManager = new DatabaseTagsManager();
    }

    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }

    public Observable<Boolean> isTagsListNotEmpty(){
        return databaseManager.readAllRecord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(tags -> tags.size() > 0);
    }
}
