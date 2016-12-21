package game.ivan.ashancalculator.items.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.items.presenter.ItemsPresenter;
import game.ivan.ashancalculator.items.view.ItemsView;

/**
 * Created by ivan on 21.12.16.
 */

public class ItemsController extends MvpController<ItemsView,ItemsPresenter> implements ItemsView,MaterialDialog.SingleButtonCallback {

    private Unbinder unbinder;
    @BindView(R.id.list)
    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;

    public ItemsController(){
        setHasOptionsMenu(true);
    }

    public ItemsController(Bundle args){
        setHasOptionsMenu(true);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this,view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(View view){

    }

    @NonNull
    @Override
    public ItemsPresenter createPresenter() {
        return new ItemsPresenter();
    }

    protected  View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container){
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
            boolean wrapInScrollView = true;
            new MaterialDialog.Builder(getActivity())
                    .title("Тестовый диалог")
                    .customView(R.layout.editor_tag_layout, wrapInScrollView)
                    .positiveText("Хорошо")
                    .negativeText("Отмена")
                    .onPositive(this)
                    .show();
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
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }
}
