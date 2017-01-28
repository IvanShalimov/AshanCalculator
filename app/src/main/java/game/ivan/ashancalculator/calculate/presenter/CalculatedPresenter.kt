package game.ivan.ashancalculator.calculate.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter

import java.util.ArrayList

import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.calculate.view.CalculaterView
import game.ivan.ashancalculator.database.DatabaseCalculateManager
import game.ivan.ashancalculator.database.models.Item
import game.ivan.ashancalculator.database.models.Tags
import game.ivan.ashancalculator.service.Calculator

/**
 * Created by ivan on 03.01.17.
 */

class CalculatedPresenter : MvpBasePresenter<CalculaterView>() {

    lateinit var databaseManager: DatabaseCalculateManager
    lateinit var calculator: Calculator

    fun getTags() {
        val labels = ArrayList<String>()
        for (tag in databaseManager.readAllTags()) {
            labels.add(tag.nameTags)
        }
        if (isViewAttached)
            view!!.setSpinnerData(labels)
    }

    override fun detachView(retainPresenterInstance: Boolean) {
        super.detachView(retainPresenterInstance)
        databaseManager.destroy()
    }

    override fun attachView(view: CalculaterView) {
        super.attachView(view)
        databaseManager = DatabaseCalculateManager(AshanApplication.instance)
        calculator = Calculator()
    }

    fun getDateForScreen(position: Int) {
        var position = position
        val items = databaseManager.getItemForTag(++position)
        if (isViewAttached) {
            view!!.refreshList(items)
            view!!.showOneManPrice(calculator.oneManSum(items, databaseManager.getDivider(position)))
            view!!.showSum(calculator.sum(items))
        }
    }
}
