package game.ivan.ashancalculator.tags.controller;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.List;

import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.view.TagsView;
import io.reactivex.Observable;

/**
 * Created by ivan on 05.01.2017.
 */

public class TagStateView implements ViewState<TagsView> {
    public final static int SHOW_CONTENT=1;
    public final static int SHOW_CREATE_DIALOG = 2;
    public final static int SHOW_EDIT_DIALOG = 3;

    int currentViewState=1;

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    List<Tags> tags;

    public void setEditableTag(Tags editableTag) {
        this.editableTag = editableTag;
    }

    Tags editableTag;

    @Override
    public void apply(TagsView view, boolean retained) {
        switch (currentViewState){
            case SHOW_CONTENT:
                view.refreshList(Observable.just(tags));
                break;
            case SHOW_CREATE_DIALOG:
                view.refreshList(Observable.just(tags));
                view.showCreateDialog();
                break;
            case SHOW_EDIT_DIALOG:
                view.refreshList(Observable.just(tags));
                view.showEditDialog(editableTag);
                break;
        }
    }

    public void setShowContentState(){
        currentViewState=SHOW_CONTENT;
    }

    public void setShowCreateDialogState(){
        currentViewState=SHOW_CREATE_DIALOG;
    }

    public void setShowEditDialogState(){
        currentViewState=SHOW_EDIT_DIALOG;
    }
}
