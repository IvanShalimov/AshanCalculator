package game.ivan.ashancalculator.tags.view

import com.hannesdorfmann.mosby.mvp.MvpView

import game.ivan.ashancalculator.database.models.Tags

/**
 * Created by ivan on 20.12.16.
 */

interface TagsView : MvpView {

    fun refreshList(list: List<Tags>)

    fun showEditDialog(tag: Tags)

    fun showCreateDialog()
}
