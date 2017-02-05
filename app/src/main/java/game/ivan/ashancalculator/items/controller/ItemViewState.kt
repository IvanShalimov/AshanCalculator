package game.ivan.ashancalculator.items.controller

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import game.ivan.ashancalculator.database.models.Item
import game.ivan.ashancalculator.items.view.ItemsView

/**
 * Created by ivan on 05.02.2017.
 */
class ItemViewState: ViewState<ItemsView> {
    companion object{
        @JvmStatic val SHOW_CONTENT = 0
        @JvmStatic val SHOW_CREATE_DIALOG = 1
        @JvmStatic val SHOW_EDIT_DIALOG = 2
    }

    lateinit internal var editableItem: Item
    internal var items: List<Item> = listOf()
    internal var currentViewState = 0

    fun setItemsList(list:List<Item>){
        items = list
    }

    fun setEditItem(item:Item){
        editableItem = item;
    }

    override fun apply(view: ItemsView?, retained: Boolean) {
        when (currentViewState) {
            SHOW_CONTENT -> view?.refreshView(items)
            SHOW_CREATE_DIALOG -> {
                view?.refreshView(items)
                view?.showCreateDialog()
            }
            SHOW_EDIT_DIALOG -> {
                view?.refreshView(items)
                view?.showEditDialog(editableItem)
            }
        }
    }

    fun setShowContent() {
        currentViewState = SHOW_CONTENT
    }

    fun setShowEditDialog() {
        currentViewState = SHOW_EDIT_DIALOG
    }

    fun setShowCreateDialog() {
        currentViewState = SHOW_CREATE_DIALOG
    }

}
