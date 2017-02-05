package game.ivan.ashancalculator.calculate.controller

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import game.ivan.ashancalculator.calculate.view.CalculaterView

/**
 * Created by ivan on 28.01.2017.
 */
class CalculatorViewState: ViewState<CalculaterView> {

    companion object{
        @JvmStatic val  SHOW_CONTENT:Int = 0
    }

    var currentState:Int = 0

    lateinit var labels:List<String>


    override fun apply(view: CalculaterView?, retained: Boolean) {
        when(currentState){
            SHOW_CONTENT -> view!!.setSpinnerData(labels)
        }
    }

}
