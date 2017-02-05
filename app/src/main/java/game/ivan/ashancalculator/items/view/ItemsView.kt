package game.ivan.ashancalculator.items.view

import com.hannesdorfmann.mosby.mvp.MvpView

import game.ivan.ashancalculator.database.models.Item

/**
 * Created by ivan on 21.12.16.
 */

interface ItemsView : MvpView {

    fun refreshView(list: List<Item>)

    fun setImagePath(path: String)

    fun showEditDialog(item: Item)

    fun showCreateDialog()
}
