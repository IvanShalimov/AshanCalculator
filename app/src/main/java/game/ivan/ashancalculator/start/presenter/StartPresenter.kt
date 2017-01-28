package game.ivan.ashancalculator.start.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter

import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.DatabaseTagsManager
import game.ivan.ashancalculator.start.view.StartView

/**
 * Created by ivan on 19.12.16.
 */

open class StartPresenter() : MvpBasePresenter<StartView>() {

    internal var databaseManager: DatabaseTagsManager

    init {
        databaseManager = DatabaseTagsManager(AshanApplication.instance)
    }

    override fun detachView(retainPresenterInstance: Boolean) {
        super.detachView(retainPresenterInstance)
    }

    fun isTagsListNotEmpty(): Boolean = databaseManager.readAllRecord().isNotEmpty()
}
