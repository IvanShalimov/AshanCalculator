package game.ivan.ashancalculator.items.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Item;

/**
 * Created by ivan on 31.12.16.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    List<Item> items;
    ItemsListAdapterCallback callback;

    public ItemListAdapter(){
        items = new ArrayList<>();
    }

    public ItemListAdapter(List<Item> list){
        items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ItemListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameLabel.setText(items.get(position).name);
        holder.priceLabel.setText(String.valueOf(
                items.get(position).price*items.get(position).count));

        Picasso.Builder builder = new Picasso.Builder(AshanApplication.instance);
        builder.listener((picasso, uri, exception) -> {
            exception.printStackTrace();
        });

        try {
            builder.build().load("file://"+items.get(position).photoPath)
                    .resize(100,100)
                    .rotate(90)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.itemIcon);

        }catch(Exception exception){
            exception.printStackTrace();
        }

        holder.item_container.setOnClickListener(view -> {
            if(callback != null){
                callback.onListItemSelect(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.price_label)
        TextView priceLabel;
        @BindView(R.id.name_label)
        TextView nameLabel;
        @BindView(R.id.item_list_container)
        RelativeLayout item_container;
        @BindView(R.id.item_icon)
        ImageView itemIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    interface ItemsListAdapterCallback {
        void onListItemSelect(Item item);
    }

    public void setCallback(ItemsListAdapterCallback callback) {
        this.callback = callback;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
