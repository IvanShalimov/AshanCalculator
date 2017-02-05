package game.ivan.ashancalculator.tags.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.DatabaseTagsManager
import game.ivan.ashancalculator.database.models.Tags
import game.ivan.ashancalculator.tags.view.TagsView

/**
 * Created by ivan on 05.02.2017.
 */
class TagsPresenter(): MvpBasePresenter<TagsView>(){

    lateinit var databaseManager: DatabaseTagsManager

    fun addTag(tag: Tags){
        databaseManager.insertTag(tag)
        loadTags(false)
    }

    fun loadTags(lock:Boolean){
        if(!lock && isViewAttached){
            view!!.refreshList(databaseManager.readAllRecord())
        }
    }

    fun deleteTag(tag:Tags){
        databaseManager.delteTag(tag)
        loadTags(false)
    }

    override fun attachView(view: TagsView?) {
        super.attachView(view)
        databaseManager = DatabaseTagsManager(AshanApplication.instance)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        databaseManager.destroy()
    }
}