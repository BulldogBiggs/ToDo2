package com.example.todo2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>  {

    public interface OnLongCickListener{
        void onItemLongClicked(int positon);
    }

    List<String> items;
    OnLongCickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongCickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }
    //Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //Grab the item at the position
    String item = items.get(position);
    //Bind the item to a particular view holder
    holder.bind(item);
    }

    //Tell the recycler view how many items are in the list
    @Override
    public int getItemCount() {

        return items.size();
    }

    //Container to provide easy access to views that represents each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
        //Update the view inside the view holder with this data
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
