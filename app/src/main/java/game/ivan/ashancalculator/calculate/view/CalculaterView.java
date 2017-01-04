package game.ivan.ashancalculator.calculate.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by ivan on 03.01.17.
 */

public interface CalculaterView extends MvpView {
    void setSpinnerData(List<String> list);
}
