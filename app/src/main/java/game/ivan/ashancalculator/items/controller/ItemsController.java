package game.ivan.ashancalculator.items.controller;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.hannesdorfmann.mosby.conductor.viewstate.MvpViewStateController;
import com.hannesdorfmann.mosby.mvp.conductor.MvpController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.items.presenter.ItemsPresenter;
import game.ivan.ashancalculator.items.view.ItemsView;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivan on 21.12.16.
 */

public class ItemsController extends MvpViewStateController<ItemsView, ItemsPresenter,ItemViewState>
        implements ItemsView,
        MaterialDialog.SingleButtonCallback, ItemListAdapter.ItemsListAdapterCallback,
        PermissionListener {

    private static final String EMPTY_STRING = "";
    public static final int DEFAULT_DOUBLE_VALUE = 1;
    private static int REQUEST_TAKE_PHOTO = 1234;
    @BindString(R.string.no_name_item)
    String noNameItem;
    String[] unitLabel;

    private Unbinder unbinder;

    @BindView(R.id.list)
    RecyclerView list;
    private ItemListAdapter adapter;
    private View dialogAdd;
    private String picturePath = EMPTY_STRING;

    public ItemsController() {
        setHasOptionsMenu(true);
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    private void onViewBound(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        adapter = new ItemListAdapter();
        adapter.setCallback(this);
        list.setAdapter(adapter);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        unitLabel = getActivity().getResources().getStringArray(R.array.unit_label);
    }

    @NonNull
    @Override
    public ItemsPresenter createPresenter() {
        return new ItemsPresenter();
    }

    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.list_layout, container, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete_all_item:
                presenter.clearAll();
                break;
            case R.id.add_item:
                new TedPermission(getApplicationContext())
                        .setPermissionListener(this)
                        .setDeniedMessage("Если вы отвергаете разрешение, вы не можете воспользоваться этой услугой" +
                                "\n\nПожалуйста, включите разрешения на [Настройки] > [Разрешение]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

        String name = ((EditText) dialogAdd.findViewById(R.id.name_item_edit_field))
                .getText().toString();
        if(name.equals(EMPTY_STRING)){
            name = noNameItem;
        }
        long tagId = (
                (fr.ganfra.materialspinner.MaterialSpinner) dialogAdd
                .findViewById(R.id.tag_spinner_list)
        )
                .getSelectedItemId();

            double count;
        try{
             count = Double.valueOf(((EditText) dialogAdd.findViewById(R.id.count_item_picker))
                    .getText().toString());
        }catch(NumberFormatException | NullPointerException exception){
            count= DEFAULT_DOUBLE_VALUE;
        }
            double price;
        try{
            price = Double.valueOf(((EditText) dialogAdd.findViewById(R.id.price_item_field))
                    .getText().toString());
        }catch(NumberFormatException | NullPointerException exception){
            price = 1;
        }

        long unitLabelId = (
                (fr.ganfra.materialspinner.MaterialSpinner) dialogAdd
                        .findViewById(R.id.unit_spinner)
        )
                .getSelectedItemId();

        if(unitLabelId == 0){
            unitLabelId = 1;
        }


        int selectedItemUnit = (int)unitLabelId-1;
        if(!picturePath.equals(EMPTY_STRING)) {
            presenter.saveItem(new Item(name, picturePath, tagId, count, price,unitLabel[selectedItemUnit]));

            picturePath = EMPTY_STRING;
        }

    }

    @Override
    public void onListItemSelect(Item item) {
        getViewState().setShowEditDialog();
        getViewState().setEditableItem(item);
        showEditDialog(item);
    }

    @Override
    public void onPermissionGranted() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(getActivity(), R.string.permission_denied + deniedPermissions.toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            try{
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    presenter.saveImageFile(bitmap);
                    if (getViewState()!= null)
                        getViewState().setShowCreateDialog();
                    showCreateDialog();
                } else {
                    Toast.makeText(this.getApplicationContext(), R.string.picture_not_taken, Toast.LENGTH_SHORT)
                            .show();
                }
            }catch(NullPointerException exception){
                //Press back button
            }
        }

    }

    @Override
    public void refreshView(Observable<List<Item>> listObservable) {
        listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list1 -> {
                    adapter.setItems(list1);
                    getViewState().setItems(list1);
                    getViewState().setShowContent();
                });
    }

    @Override
    public void setImagePath(String path) {
        this.picturePath = path;
    }

    @Override
    public void showEditDialog(Item item) {
        MaterialDialog dialog;
        boolean wrapInScrollView = true;
        dialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.edit_title_item_dialog)
                .customView(R.layout.add_item_dialog_layout, wrapInScrollView)
                .positiveText(R.string.save)
                .negativeText(R.string.delete)
                .onPositive((dialog12, which) -> {
                    String name = ((EditText) dialogAdd.findViewById(R.id.name_item_edit_field))
                            .getText().toString();
                    if(name.equals(EMPTY_STRING)){
                        name = noNameItem;
                    }
                    item.name = name;
                    item.tag_id = ((Spinner) dialogAdd.findViewById(R.id.tag_spinner_list)).getSelectedItemId();
                    try {
                        item.count = Double.valueOf(((EditText) dialogAdd.findViewById(R.id.count_item_picker))
                                .getText().toString());
                    }catch(NumberFormatException | NullPointerException exception){
                        item.count = DEFAULT_DOUBLE_VALUE;
                    }


                    try {
                        item.price = Double.valueOf(((EditText) dialogAdd.findViewById(R.id.price_item_field))
                                .getText().toString());
                    }catch(NumberFormatException | NullPointerException exception){
                        item.price = DEFAULT_DOUBLE_VALUE;
                    }

                    long unitLabelId = (
                            (fr.ganfra.materialspinner.MaterialSpinner) dialogAdd
                                    .findViewById(R.id.unit_spinner)
                    )
                            .getSelectedItemId();


                    int selectedItemUnit = (int)unitLabelId-1;
                    item.unit = unitLabel[selectedItemUnit];

                    presenter.saveItem(item);
                })
                .onNegative((dialog1, which) -> {
                    presenter.deleteItem(item);
                })
                .build();

        dialogAdd = dialog.getCustomView();
        presenter.getListTag().subscribe(strings -> {
            spinnerAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    R.layout.spinner_item, strings);
            ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.tag_spinner_list)).setAdapter(spinnerAdapter);

        });

        ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.tag_spinner_list)).setSelection((int) item.tag_id);

        ArrayAdapter<String> unitSpinnerAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.spinner_item, unitLabel);
        ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.unit_spinner)).setAdapter(unitSpinnerAdapter);

        for(int i =0;i<unitLabel.length;i++){
            if(unitLabel[i].equals(item.unit)){
                Log.d("Test","i = " + i);
                int select = i+1;
                ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.unit_spinner)).setSelection(select);
                break;
            }
        }


        ((EditText) dialogAdd.findViewById(R.id.name_item_edit_field)).setText(String.valueOf(item.name));
        ((EditText) dialogAdd.findViewById(R.id.count_item_picker)).setText(String.valueOf(item.count));
        ((EditText) dialogAdd.findViewById(R.id.price_item_field)).setText(String.valueOf(item.price));

        dialog.show();
    }

    ArrayAdapter<String> spinnerAdapter;

    @Override
    public void showCreateDialog() {
        MaterialDialog dialog;
        boolean wrapInScrollView = true;

        dialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.add_bag)
                .customView(R.layout.add_item_dialog_layout, wrapInScrollView)
                .positiveText(R.string.add)
                .negativeText(R.string.editor_cancel_button_label)
                .onPositive(this)
                .build();

        dialogAdd = dialog.getCustomView();


        presenter.getListTag().subscribe(strings -> {
            spinnerAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    R.layout.spinner_item, strings);

            ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.tag_spinner_list)).setAdapter(spinnerAdapter);
        } );



        ArrayAdapter<String> unitSpinnerAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.spinner_item, unitLabel);
        ((fr.ganfra.materialspinner.MaterialSpinner) dialogAdd.findViewById(R.id.unit_spinner)).setAdapter(unitSpinnerAdapter);


        dialog.show();
    }

    @NonNull
    @Override
    public ItemViewState createViewState() {
        return new ItemViewState();
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {

    }

    @Override
    public void onNewViewStateInstance() {
        presenter.loadItems(false);
    }
}
