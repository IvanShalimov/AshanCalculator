package game.ivan.ashancalculator.items.controller;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.AshanApplication;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Item;
import game.ivan.ashancalculator.database.models.Tags;

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

        Log.d("Test","path = "+items.get(position).photoPath);

        Picasso.Builder builder = new Picasso.Builder(AshanApplication.getInstante());
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                Log.d("Test","imageLoadFailed");
                exception.printStackTrace();
            }
        });

        try {
            builder.build().load("file://"+items.get(position).photoPath)
                    .resize(50, 50)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(holder.itemIcon);
           // Picasso.with(AshanApplication.getInstante())

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
