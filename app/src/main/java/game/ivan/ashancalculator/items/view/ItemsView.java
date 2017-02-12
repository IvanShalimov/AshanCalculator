package game.ivan.ashancalculator.items.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import game.ivan.ashancalculator.database.models.Item;
import io.reactivex.Observable;

/**
 * Created by ivan on 21.12.16.
 */

public interface ItemsView extends MvpView {

    void refreshView(Observable<List<Item>> list);

    void setImagePath(String path);

    void showEditDialog(Item item);

    void showCreateDialog();
}
