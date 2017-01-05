package game.ivan.ashancalculator.tags.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.tags.presenter.TagsPresenter;
import game.ivan.ashancalculator.tags.view.TagsView;

/**
 * Created by ivan on 20.12.16.
 */

public class TagsController extends MvpViewStateController<TagsView, TagsPresenter,TagStateView> implements TagsView,
        MaterialDialog.SingleButtonCallback, TagsListAdapter.TagsListAdapterCallback {

    private Unbinder unbinder;
    @BindView(R.id.list)
    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;
    /*Адптер списка*/
    TagsListAdapter adapter;
    /*Маке идалога*/
    View dialogAdd;
    MaterialDialog dialog;


    public TagsController() {
        setHasOptionsMenu(true);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }

    public TagsController(Bundle args) {
        setHasOptionsMenu(true);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
    }

    protected void onViewBound(View view) {

        layoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        adapter = new TagsListAdapter();
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
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        View layout = dialog.getCustomView();
        String name =
                ((EditText) layout.findViewById(R.id.name_edit_field))
                        .getText()
                        .toString();

        int division = Integer
                .valueOf(
                        ((EditText) layout.findViewById(R.id.count_edit_field))
                                .getText()
                                .toString());
        presenter.addTag(new Tags(name, division));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void refreshList(List<Tags> list) {
        adapter.setTags(list);
        getViewState().setShowContentState();
        getViewState().setTags(list);
    }

    @Override
    public void showEditDialog(Tags tag) {
        boolean wrapInScrollView = true;
        if (dialogAdd == null) {

            dialog = new MaterialDialog.Builder(getActivity())
                    .title("Изменить тэг")
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText("Изменить тэг")
                    .negativeText("Удалить")
                    .onPositive((dialog12, which) -> presenter.addTag(tag))
                    .onNegative((dialog12, which) -> presenter.deleteTag(tag))
                    .build();

            dialogAdd = dialog.getCustomView();


        } else {
            dialog = new MaterialDialog.Builder(getActivity())
                    .title("Изменить тэг")
                    .customView(dialogAdd, wrapInScrollView)
                    .positiveText("Изменить")
                    .negativeText("Удалить")
                    .onPositive((dialog1, which) -> presenter.addTag(tag))
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
                    .title("Добавить тэг")
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText("Добавить")
                    .negativeText("Отмена")
                    .onPositive(this)
                    .build();
        else
            dialog = new MaterialDialog.Builder(getActivity())
                    .title("Добавить тэг")
                    .customView(dialogAdd, wrapInScrollView)
                    .positiveText("Добавить")
                    .negativeText("Отмена")
                    .onPositive(this)
                    .build();

        dialogAdd = dialog.getCustomView();

        ((EditText) dialog.findViewById(R.id.name_edit_field)).setText("");
        ((EditText) dialog.findViewById(R.id.count_edit_field)).setText("");
        dialog.show();
    }

    @Override
    public void onListItemSelect(final Tags tag) {
        Log.d("Test","tag item list select");
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
