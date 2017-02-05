package game.ivan.ashancalculator.items.presenter

import android.graphics.Bitmap
import android.os.Environment
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import game.ivan.ashancalculator.AshanApplication
import game.ivan.ashancalculator.database.DatabaseItemsManager
import game.ivan.ashancalculator.database.models.Item
import game.ivan.ashancalculator.items.view.ItemsView
import game.ivan.ashancalculator.service.RotateManager
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Created by ivan on 05.02.2017.
 */
class ItemsPresenter: MvpBasePresenter<ItemsView>(){

    lateinit  internal var databaseManager: DatabaseItemsManager

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        databaseManager.destroy()
    }

    override fun attachView(view: ItemsView?) {
        super.attachView(view)
        databaseManager = DatabaseItemsManager(AshanApplication.instance)
    }

    fun getListTag():List<String>{
        val listTags = databaseManager.tagsList
        var labels = ArrayList<String>()
        for (tag in listTags) {
            labels.add(tag.nameTags)
        }
        return labels
    }

    fun saveItems(item:Item){
        databaseManager.insertItem(item)
        loadItems(false)
    }

    fun loadItems(lock:Boolean){
        if(!lock && isViewAttached){
            view?.refreshView(databaseManager.readAllRecord())
        }
    }

    fun deleteItem(item:Item){
        databaseManager.deleteItem(item)
        loadItems(false)
    }

    fun clearAll(){
        databaseManager.deleteAll()
        loadItems(false)
    }

    fun saveImageFile(bitmap:Bitmap){
        val root = Environment.getExternalStorageDirectory().toString()
        val myDirectory = File(root + "/saved_images")
        if (!myDirectory.exists()) {
            myDirectory.mkdir()
        }

        val fileName = "Image-" + System.nanoTime() + ".jpg"
        val file = File(myDirectory, fileName)

        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()

            if (isViewAttached)
                view!!.setImagePath(file.absolutePath)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        RotateManager.checkRotation(file.absolutePath, bitmap)

    }
}