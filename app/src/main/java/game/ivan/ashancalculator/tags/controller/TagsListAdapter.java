package game.ivan.ashancalculator.tags.controller;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import game.ivan.ashancalculator.R;
import game.ivan.ashancalculator.database.models.Tags;

/**
 * Created by ivan on 27.12.16.
 */

public class TagsListAdapter extends RecyclerView.Adapter<TagsListAdapter.ViewHolder> {

    List<Tags> tags;
    TagsListAdapterCallback callback;

    public TagsListAdapter(){
        tags = new ArrayList<>();
    }

    public TagsListAdapter(List<Tags> list){
        tags = list;
    }

    public void setTags(List<Tags> tags) {
        if(tags != null)
            this.tags = tags;
        else
            this.tags = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tag_item_layout, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemTagLabel.setText(""+tags.get(position).nameTags);
        holder.itemTagLabel.setTextColor(Color.BLACK);

        holder.item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null){
                    callback.onListItemSelect(tags.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_tag_label)
        TextView itemTagLabel;
        @BindView(R.id.tag_item_container)
        LinearLayout item_container;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    interface TagsListAdapterCallback {
        void onListItemSelect(Tags tag);
    }

    public void setCallback(TagsListAdapterCallback callback) {
        this.callback = callback;
    }

}
