package game.ivan.ashancalculator.tags.controller

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import game.ivan.ashancalculator.database.models.Tags
import game.ivan.ashancalculator.tags.view.TagsView

/**
 * Created by ivan on 05.02.2017.
 */
class TagStateView: ViewState<TagsView>{


    companion object{
        @JvmStatic val  SHOW_CONTENT:Int = 1
        @JvmStatic val  SHOW_CREATE_DIALOG:Int = 2
        @JvmStatic val  SHOW_EDIT_DIALOG:Int = 3
    }

    var currentViewState:Int = 1
    lateinit var tages:List<Tags>
    lateinit var editTags:Tags

    fun setTags(list:List<Tags>){
        tages = list
    }

    fun setEditableTag(tag:Tags){
        editTags = tag
    }

    override fun apply(view: TagsView?, retained: Boolean) {
        when(currentViewState){
            SHOW_CONTENT ->  view!!.refreshList(tages)
            SHOW_CREATE_DIALOG ->  {
                view!!.refreshList(tages)
                view!!.showCreateDialog()}
            SHOW_EDIT_DIALOG ->  {
                view!!.refreshList(tages)
                view!!.showEditDialog(editTags)
            }
        }
    }

    fun setShowContentState(){
        currentViewState=SHOW_CONTENT
    }

    fun setShowCreateDialogState(){
        currentViewState=SHOW_CREATE_DIALOG
    }

    fun setShowEditDialogState(){
        currentViewState=SHOW_EDIT_DIALOG
    }

}