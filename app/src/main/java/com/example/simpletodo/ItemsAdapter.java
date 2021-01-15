package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

public interface OnLongClickListener {
    void onItemLongClicked(int position);
}
    List<String> items;
OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items,OnLongClickListener longClickListener) {
        this.items = items;
       this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use  layout inflator to inflate a view
      View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a View holder and return it
        return new ViewHolder(todoView);

    }


    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // grab the item at position
        String item = items.get(position);
        //Bind the item into the specific view holder
        holder.bind(item);
    }
    // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          tvItem = itemView.findViewById(android.R.id.text1);
      }

      public void bind(String item) {
          tvItem.setText(item);
          tvItem.setOnLongClickListener ( v -> {
               // Notify the listen
               longClickListener.onItemLongClicked(getAdapterPosition());
               return true;
           } );
      }
  }
}
