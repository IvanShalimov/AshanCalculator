package game.ivan.ashancalculator.calculate.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import game.ivan.ashancalculator.database.models.Item;

/**
 * Created by ivan on 03.01.17.
 */

public interface CalculaterView extends MvpView {
    void setSpinnerData(List<String> list);
    void refreshList(List<Item> list);
    void showOneManPrice(double price);
    void showSum(double price);
}
