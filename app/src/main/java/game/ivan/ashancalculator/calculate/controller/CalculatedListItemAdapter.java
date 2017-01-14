package game.ivan.ashancalculator.calculate.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Item;

/**
 * Created by ivan on 04.01.2017.
 */

public class CalculatedListItemAdapter extends RecyclerView.Adapter<CalculatedListItemAdapter.ViewHolder> {

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    List<Item> items;

    public CalculatedListItemAdapter(){
        items = new ArrayList<>();
    }

    public CalculatedListItemAdapter(List<Item> list){
        items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calculated_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(items.get(position).name);
        holder.count.setText(String.valueOf(items.get(position).count)+" "+items.get(position).unit);
        holder.price.setText(String.valueOf(items.get(position).count*items.get(position).price));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.name_calculated_item)
        TextView name;
        @BindView(R.id.count_calculated_item)
        TextView count;
        @BindView(R.id.price_calculated_item)
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
