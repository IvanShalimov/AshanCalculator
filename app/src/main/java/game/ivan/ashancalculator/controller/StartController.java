package game.ivan.ashancalculator.controller;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.presenter.StartPresenter;
import game.ivan.ashancalculator.view.StartView;

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

    @Override
    public void showText(String text) {
        testTextView.setText(text);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this,view);
        return view;
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
        presenter.returnText(0);
    }

    @OnClick(R.id.add_tag_button)
    public void addTagButtonClick(View view){
        presenter.returnText(1);
    }

    @OnClick(R.id.clear_bag_buton)
    public void clearingButtonClick(View view){
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
}
