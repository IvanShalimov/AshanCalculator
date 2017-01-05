package game.ivan.ashancalculator.calculate.controller;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import game.ivan.ashancalculator.calculate.view.CalculaterView;

/**
 * Created by ivan on 04.01.2017.
 */

public class CalculatorViewState implements ViewState<CalculaterView> {

    public final static int SHOW_CONTENT = 0;

    public final static int NOT_SHOW_CONTENT = 1;

    int currentState;

    @Override
    public void apply(CalculaterView view, boolean retained) {

    }


}
