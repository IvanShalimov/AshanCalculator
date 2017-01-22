package game.ivan.ashancalculator.items.presenter;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.database.DatabaseItemsManager;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.items.view.ItemsView;
import game.ivan.ashancalculator.service.RotateManager;

/**
 * Created by ivan on 21.12.16.
 */

public class ItemsPresenter extends MvpBasePresenter<ItemsView> {

    DatabaseItemsManager databaseManager;
    public ItemsPresenter(){

    }

    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
        databaseManager.destroy();
    }

    @Override
    public void attachView(ItemsView view) {
        super.attachView(view);
        databaseManager = new DatabaseItemsManager(AshanApplication.getInstante());
    }

    public List<String> getListTag(){
        List<Tags> listTags = databaseManager.getTagsList();
        List<String> labels = new ArrayList<>();
        for(Tags tag: listTags){
            labels.add(tag.nameTags);
        }
        return labels;
    }

    public void saveItem(Item item){
        databaseManager.insertItem(item);
        loadItems(false);
    }

    public void loadItems(boolean lock){
        if(!lock){
            if(isViewAttached())
                getView().refreshView(databaseManager.readAllRecord());
        }
    }

    public void deleteItem(Item item){
        databaseManager.deleteItem(item);
        loadItems(false);
    }

    public void clearAll(){
        databaseManager.deleteAll();
        loadItems(false);
    }


    public void saveImageFile(Bitmap bitmap){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDirectory = new File(root + "/saved_images");
        if(!myDirectory.exists()){
             myDirectory.mkdir();
        }

        String fileName = "Image-"+ System.nanoTime() +".jpg";
        File file = new File (myDirectory, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            if(isViewAttached())
                getView().setImagePath(file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        RotateManager.INSTANCE.checkRotation(file.getAbsolutePath(),bitmap);
    }
}
