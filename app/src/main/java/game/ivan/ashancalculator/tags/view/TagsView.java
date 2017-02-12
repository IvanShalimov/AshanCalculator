package game.ivan.ashancalculator.tags.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import game.ivan.ashancalculator.database.models.Tags;
import io.reactivex.Observable;

/**
 * Created by ivan on 20.12.16.
 */

public interface TagsView extends MvpView {

    void refreshList(Observable<List<Tags>> list);

    public void showEditDialog(Tags tag);

    void showCreateDialog();
}
