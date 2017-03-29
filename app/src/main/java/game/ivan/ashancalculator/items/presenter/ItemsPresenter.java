package game.ivan.ashancalculator.items.presenter;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import game.ivan.ashancalculator.database.DatabaseItemsManager;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;
import game.ivan.ashancalculator.items.view.ItemsView;
import game.ivan.ashancalculator.service.RotateManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivan on 21.12.16.
 */

public class ItemsPresenter extends MvpBasePresenter<ItemsView> {

    DatabaseItemsManager databaseManager;

    public ItemsPresenter(){
        databaseManager = new DatabaseItemsManager();
    }

    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
    }

    public Observable<List<String>> getListTag(){
        return databaseManager.getTagsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(tagses -> {
                    List<String> labels = new ArrayList<>();
                    for(Tags tag: tagses){
                        labels.add(tag.nameTags);
                    }
                    return labels;
                });
    }

    public void saveItem(Item item){
        databaseManager.insertItem(item);
        loadItems(false);
    }

    public void loadItems(boolean lock){
        if(!lock && isViewAttached()){
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
        //TODO refactor
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

        RotateManager.checkRotation(file.getAbsolutePath(),bitmap);
    }
}
