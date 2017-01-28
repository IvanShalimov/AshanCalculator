package game.ivan.ashancalculator.calculate.view

import com.hannesdorfmann.mosby.mvp.MvpView

import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 03.01.17.
 */

interface CalculaterView : MvpView {
    fun setSpinnerData(list: List<String>)
    fun refreshList(list: List<Item>)
    fun showOneManPrice(price: Double)
    fun showSum(price: Double)
}
