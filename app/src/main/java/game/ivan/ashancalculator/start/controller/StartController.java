package game.ivan.ashancalculator.start.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.items.controller.ItemsController;
import game.ivan.ashancalculator.service.ActionBarProvider;
import game.ivan.ashancalculator.start.presenter.StartPresenter;
import game.ivan.ashancalculator.start.view.StartView;
import game.ivan.ashancalculator.tags.controller.TagsController;

/**
 * Created by ivan on 19.12.16.
 */

public class StartController extends MvpController<StartView,StartPresenter> implements StartView {

    @BindView(R.id.add_item_button)
    Button addItemButton;
    @BindView(R.id.add_tag_button)
    Button addTagButton;
    @BindView(R.id.clear_bag_buton)
    Button clearItemsButton;
    @BindView(R.id.test_status_click)
    TextView testTextView;
    private Unbinder unbinder;

    public StartController(){
        setHasOptionsMenu(false);
    }

    public StartController(Bundle args){
        setHasOptionsMenu(false);
    }

    @Override
    public void showText(String text) {
        testTextView.setText(text);
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

    protected ActionBar getActionBar() {
        ActionBarProvider actionBarProvider = ((ActionBarProvider)getActivity());
        return actionBarProvider != null ? actionBarProvider.getSupportActionBar() : null;
    }

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return new StartPresenter();
    }

    protected  View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container){
        return inflater.inflate(R.layout.start_controller_layout, container, false);
    }

    @OnClick(R.id.add_item_button)
    public void addItemButtonClick(View view){
        getRouter().pushController(
                RouterTransaction.with(new ItemsController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
    }

    @OnClick(R.id.add_tag_button)
    public void addTagButtonClick(View view){
        getRouter().pushController(
                RouterTransaction.with(new TagsController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
    }

    @OnClick(R.id.clear_bag_buton)
    public void clearingButtonClick(View view){
       //Заглушка
        presenter.returnText(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_start, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
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
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);
    }
}
