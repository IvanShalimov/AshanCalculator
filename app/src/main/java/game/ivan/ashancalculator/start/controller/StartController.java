package game.ivan.ashancalculator.start.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.calculate.controller.CalculateController;
import game.ivan.ashancalculator.items.controller.ItemsController;
import game.ivan.ashancalculator.service.ActionBarProvider;
import game.ivan.ashancalculator.start.PicassoCallback;
import game.ivan.ashancalculator.start.presenter.StartPresenter;
import game.ivan.ashancalculator.start.view.StartView;
import game.ivan.ashancalculator.tags.controller.TagsController;

/**
 * Created by ivan on 19.12.16.
 */

public class StartController extends MvpController<StartView, StartPresenter> implements StartView {

    @BindView(R.id.add_item_button)
    FloatingActionButton addItemButton;
    @BindView(R.id.add_tag_button)
    FloatingActionButton addTagButton;
    @BindView(R.id.clear_bag_button)
    FloatingActionButton clearItemsButton;

    private Unbinder unbinder;

    public StartController() {
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(View view) {
        getActionBar().setIcon(R.mipmap.ic_launcher);
    }

    protected ActionBar getActionBar() {
        ActionBarProvider actionBarProvider = ((ActionBarProvider) getActivity());
        return actionBarProvider != null ? actionBarProvider.getSupportActionBar() : null;
    }

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return new StartPresenter();
    }

    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.start_controller_layout, container, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
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

    @Override
    public void openProductBag() {
        getRouter().pushController(RouterTransaction.with(new ItemsController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));

    }

    @Override
    public void openTagEditor() {
        getRouter().pushController(RouterTransaction.with(new TagsController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void openCalculated() {
        getRouter().pushController(RouterTransaction.with(new CalculateController())
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void showWarningMessage() {
        Toast.makeText(getApplicationContext(),R.string.please_add_tags, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.clear_bag_button, R.id.add_tag_button, R.id.add_item_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_bag_button:
                presenter.isTagsListNotEmpty()
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                openCalculated();
                            } else {
                                showWarningMessage();
                            }
                        });
                break;
            case R.id.add_tag_button:
                openTagEditor();
                break;
            case R.id.add_item_button:
                presenter.isTagsListNotEmpty()
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                openProductBag();
                            } else {
                                showWarningMessage();
                            }
                        });
                break;
        }
    }
}
