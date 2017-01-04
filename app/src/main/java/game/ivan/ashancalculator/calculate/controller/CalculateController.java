package game.ivan.ashancalculator.calculate.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.calculate.presenter.CalculatedPresenter;
import game.ivan.ashancalculator.calculate.view.CalculaterView;
import game.ivan.ashancalculator.items.controller.ItemListAdapter;

/**
 * Created by ivan on 03.01.17.
 */

public class CalculateController extends MvpController<CalculaterView,CalculatedPresenter>
        implements CalculaterView,AdapterView.OnItemSelectedListener {

    private Unbinder unbinder;
    @BindView(R.id.tag_spinner)
    Spinner tagSpinner;
    @BindView(R.id.list_header_label)
    TextView headerLsit;
    @BindView(R.id.one_man_label)
    TextView oneManPrice;
    @BindView(R.id.sum_label)
    TextView sum;
    @BindView(R.id.list_item_position)
    RecyclerView itemPositionList;
    RecyclerView.LayoutManager layoutManager;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(View view) {

    }

    @NonNull
    @Override
    public CalculatedPresenter createPresenter() {
        return new CalculatedPresenter();
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.getTags();
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
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void setSpinnerData(List<String> list) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.spinner_item, list);
        tagSpinner.setAdapter(spinnerAdapter);
        tagSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.getDateForScreen(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
