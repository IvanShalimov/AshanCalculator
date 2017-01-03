package game.ivan.ashancalculator.calculate.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.calculate.presenter.CalculatedPresenter;
import game.ivan.ashancalculator.calculate.view.CalculaterView;
import game.ivan.ashancalculator.items.controller.ItemListAdapter;

/**
 * Created by ivan on 03.01.17.
 */

public class CalculateController extends MvpController<CalculaterView,CalculatedPresenter> implements CalculaterView {

    private Unbinder unbinder;

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
}
