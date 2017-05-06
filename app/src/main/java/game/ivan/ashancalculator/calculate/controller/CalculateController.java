package game.ivan.ashancalculator.calculate.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hannesdorfmann.mosby.conductor.viewstate.MvpViewStateController;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.calculate.controller.dagger.CalculatorControllerComponent;
import game.ivan.ashancalculator.calculate.presenter.CalculatedPresenter;
import game.ivan.ashancalculator.calculate.view.CalculatorView;
import game.ivan.ashancalculator.database.models.Item;

/**
 * Created by ivan on 03.01.17.
 */

public class CalculateController extends MvpViewStateController<CalculatorView,CalculatedPresenter,CalculatorViewState>
        implements CalculatorView,AdapterView.OnItemSelectedListener {

    @BindView(R.id.tag_spinner)
    Spinner tagSpinner;
    @BindView(R.id.list_header_label)
    TextView headerList;
    @BindView(R.id.one_man_label)
    TextView oneManPrice;
    @BindView(R.id.sum_label)
    TextView sum;
    @BindView(R.id.list_item_position)
    RecyclerView itemPositionList;
    RecyclerView.LayoutManager layoutManager;
    @Inject
    CalculatedListItemAdapter adapter;
    double onePrice,sumPrice;

    CalculatorControllerComponent component;


    public CalculateController(){
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);

        component = AshanApplication.getComponent().createCalculatorControllerComponent();
        component.injectCalculateController(this);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        ButterKnife.bind(this,view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(View view) {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemPositionList.setLayoutManager(layoutManager);
        itemPositionList.setAdapter(adapter);
    }

    @NonNull
    @Override
    public CalculatedPresenter createPresenter() {
        return new CalculatedPresenter();
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
    }

    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.calculater_layout, container, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
    }

    @Override
    public void setSpinnerData(List<String> list) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.spinner_item, list);
        tagSpinner.setAdapter(spinnerAdapter);
        tagSpinner.setSelection(getViewState().currentState);
        tagSpinner.setOnItemSelectedListener(this);
        getViewState().setLabels(list);
    }

    @Override
    public void refreshList(List<Item> list) {
        adapter.setItems(list);
    }

    @Override
    public void showOneManPrice(double price) {
        onePrice = price;
        oneManPrice.setText("С одного : "+price+" RUB");
    }

    @Override
    public void showSum(double price) {
        sumPrice = price;
        sum.setText("Общее: "+price+" RUB");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getViewState().setCurrentSelectedSpinnerItem(position);
        presenter.getDateForScreen(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @NonNull
    @Override
    public CalculatorViewState createViewState() {
        return new CalculatorViewState();
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {

    }

    @Override
    public void onNewViewStateInstance() {
        presenter.getTags();
    }
}
