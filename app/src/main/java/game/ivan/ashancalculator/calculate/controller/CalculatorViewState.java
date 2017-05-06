package game.ivan.ashancalculator.calculate.controller;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.List;

import game.ivan.ashancalculator.calculate.view.CalculatorView;

/**
 * Created by ivan on 04.01.2017.
 */

public class CalculatorViewState implements ViewState<CalculatorView> {

    public final static int SHOW_CONTENT = 0;

    public final static int NOT_SHOW_CONTENT = 1;

    public void setCurrentSelectedSpinnerItem(int currentSelectedSpinnerItem) {
        this.currentSelectedSpinnerItem = currentSelectedSpinnerItem;
    }

    int currentSelectedSpinnerItem=0;

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    List<String> labels;

    int currentState=0;

    @Override
    public void apply(CalculatorView view, boolean retained) {
        switch (currentState){
            case SHOW_CONTENT:
                view.setSpinnerData(labels);
                break;
        }
    }


}
