package com.vm.weather.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vm.weather.R;
import com.vm.weather.models.DateRecyclerItem;

import java.util.List;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class DateRecyclerAdapter extends RecyclerView.Adapter<DateRecyclerViewHolder> {

    private List<DateRecyclerItem> datesList;
    private View.OnClickListener mClickListener;

    public DateRecyclerAdapter(List<DateRecyclerItem> items){
        this.datesList = items;
    }

    public void setClickListener(View.OnClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    @Override
    public DateRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DateRecyclerViewHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_recycler_item, parent, false);
        layoutView.setOnClickListener(mClickListener);

        viewHolder = new DateRecyclerViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DateRecyclerViewHolder holder, int position) {

        DateRecyclerItem item = datesList.get(position);


        holder.txtDayOfWeek.setText(item.getDayofWeek());

        holder.txtDateOfWeek.setText(item.getDateToDisplay());

        holder.itemView.setTag(item.getDateYear());

        if(item.isTodaysDate() && holder.itemView.hasOnClickListeners()){
            holder.itemView.callOnClick();
        }
        /*double mTemp = Double.parseDouble(datesList.get(position).getTemp());
        holder.txtTemp.setText(String.valueOf(Math.round(mTemp)) + "°");*/
    }
    @Override
    public int getItemCount() {
        return datesList.size();
    }


}
