package game.ivan.ashancalculator.items.controller;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.List;

import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.items.view.ItemsView;
import io.reactivex.Observable;

/**
 * Created by ivan on 05.01.2017.
 */

public class ItemViewState implements ViewState<ItemsView> {

    public final static int SHOW_CONTENT = 0;
    public final static int SHOW_CREATE_DIALOG = 1;
    public final static int SHOW_EDIT_DIALOG = 2;

    public void setEditableItem(Item editableItem) {
        this.editableItem = editableItem;
    }

    Item editableItem;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    List<Item> items;
    int currentViewState =0;

    @Override
    public void apply(ItemsView view, boolean retained) {
        switch (currentViewState)
        {
            case SHOW_CONTENT:
                view.refreshView(Observable.just(items));
                break;
            case SHOW_CREATE_DIALOG:
                view.refreshView(Observable.just(items));
                view.showCreateDialog();
                break;
            case SHOW_EDIT_DIALOG:
                view.refreshView(Observable.just(items));
                view.showEditDialog(editableItem);
                break;
        }
    }

    public void setShowContent(){
        currentViewState = SHOW_CONTENT;
    }

    public void setShowEditDialog(){
        currentViewState = SHOW_EDIT_DIALOG;
    }

    public void setShowCreateDialog(){
        currentViewState = SHOW_CREATE_DIALOG;
    }
}
