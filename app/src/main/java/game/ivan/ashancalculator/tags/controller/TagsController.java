package game.ivan.ashancalculator.tags.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hannesdorfmann.mosby.conductor.viewstate.MvpViewStateController;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.controller.dagger.TagControllerComponent;
import game.ivan.ashancalculator.tags.presenter.TagsPresenter;
import game.ivan.ashancalculator.tags.view.TagsView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static game.ivan.ashancalculator.tags.presenter.TagsPresenter.DEFAULT_DIVISION_VALUE;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsController extends MvpViewStateController<TagsView, TagsPresenter,TagStateView> implements TagsView,
        MaterialDialog.SingleButtonCallback, TagsListAdapter.TagsListAdapterCallback {


    @BindString(R.string.nameless)
    String namless;

    @BindView(R.id.list)
    RecyclerView list;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    TagsListAdapter adapter;
    View dialogAdd;
    MaterialDialog dialog;

    TagControllerComponent component;

    public TagsController() {
        setHasOptionsMenu(true);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);


    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        component = AshanApplication.getComponent().createTagControllerComponent();
        component.inhjectTagController(this);
        View view = inflateView(inflater, container);

        ButterKnife.bind(this,view);
        onViewBound();
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
    }

    private void onViewBound() {
        list.setLayoutManager(layoutManager);
        adapter.setCallback(this);
        list.setAdapter(adapter);
    }

    @NonNull
    @Override
    public TagsPresenter createPresenter() {
        return new TagsPresenter();
    }

    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.list_layout, container, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_tags, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_item) {
            getViewState().setShowCreateDialogState();
            showCreateDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        View layout = dialog.getCustomView();

        String name = ((EditText) layout.findViewById(R.id.name_edit_field))
                        .getText()
                        .toString();

        if(name.equals(""))
            name = namless;

        int division =
                presenter.checkDivision(((EditText) layout.findViewById(R.id.count_edit_field))
                        .getText().toString());

        presenter.addTag(new Tags(name, division));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void refreshList(Observable<List<Tags>> listObservable) {
        listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tags -> {
                    adapter.setTags(tags);
                    getViewState().setShowContentState();
                    getViewState().setTags(tags);
                });
    }

    @Override
    public void showEditDialog(Tags tag) {
        boolean wrapInScrollView = true;
        if (dialogAdd == null) {

            dialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.edit_tag)
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText(R.string.edit)
                    .negativeText(R.string.delete)
                    .onPositive((dialog12, which) -> {
                        String name = ((EditText)dialog.findViewById(R.id.name_edit_field)).getText().toString();
                        if(name.equals("")){
                            name = namless;
                        } else {
                            tag.nameTags = name;
                        }

                        String division = ((EditText) dialog.findViewById(R.id.count_edit_field)).getText().toString();
                        if(division.equals("")){
                            tag.divisionFactor = DEFAULT_DIVISION_VALUE;
                        } else {
                            tag.divisionFactor = Integer.valueOf(division);
                        }
                        presenter.addTag(tag);})
                    .onNegative((dialog12, which) -> presenter.deleteTag(tag))
                    .build();

            dialogAdd = dialog.getCustomView();


        } else {
            dialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.edit_tag)
                    .customView(dialogAdd, wrapInScrollView)
                    .positiveText(R.string.edit)
                    .negativeText(R.string.delete)
                    .onPositive((dialog1, which) -> {
                        String name = ((EditText)dialog.findViewById(R.id.name_edit_field)).getText().toString();
                        if(name.equals("")){
                            name = namless;
                        } else {
                            tag.nameTags = name;
                        }

                        String division = ((EditText) dialog.findViewById(R.id.count_edit_field)).getText().toString();
                        if(division.equals("")){
                            tag.divisionFactor = DEFAULT_DIVISION_VALUE;
                        } else {
                            tag.divisionFactor = Integer.valueOf(division);
                        }
                        presenter.addTag(tag);})
                    .onNegative((dialog12, which) -> presenter.deleteTag(tag))
                    .build();

        }
        ((EditText) dialog.findViewById(R.id.name_edit_field)).setText(String.valueOf(tag.nameTags));
        ((EditText) dialog.findViewById(R.id.count_edit_field)).setText(String.valueOf(tag.divisionFactor));
        dialog.show();
    }

    @Override
    public void showCreateDialog() {
        boolean wrapInScrollView = true;
        if (dialogAdd == null)
            dialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.create_tag)
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText(R.string.add_label_menu_item)
                    .negativeText(R.string.editor_cancel_button_label)
                    .onPositive(this)
                    .build();
        else
            dialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.create_tag)
                    .customView(dialogAdd, wrapInScrollView)
                    .positiveText(R.string.add_label_menu_item)
                    .negativeText(R.string.editor_cancel_button_label)
                    .onPositive(this)
                    .build();

        dialogAdd = dialog.getCustomView();

        ((EditText) dialog.findViewById(R.id.name_edit_field)).setText("");
        ((EditText) dialog.findViewById(R.id.count_edit_field)).setText("");
        dialog.show();
    }

    @Override
    public void onListItemSelect(Tags tag) {
        getViewState().setShowEditDialogState();
        getViewState().setEditableTag(tag);
        showEditDialog(tag);
    }

    @NonNull
    @Override
    public TagStateView createViewState() {
        return new TagStateView();
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {

    }

    @Override
    public void onNewViewStateInstance() {
        presenter.loadTags(false);
    }


}
