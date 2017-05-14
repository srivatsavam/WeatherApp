package com.vm.weather.adapters;

/**
 * Created by vatsavm on 14-05-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.vm.weather.R;

public class DateRecyclerViewHolder extends RecyclerView.ViewHolder{ // implements View.OnClickListener

    public TextView txtDayOfWeek;
    public TextView txtDateOfWeek;
//    public TextView txtTemp;
    public DateRecyclerViewHolder(final View itemView){
        super(itemView);
        txtDayOfWeek = (TextView) itemView.findViewById(R.id.txtDayOfWeek);
        txtDateOfWeek = (TextView) itemView.findViewById(R.id.txtDateOfWeek);
//        txtTemp = (TextView) itemView.findViewById(R.id.txtTemp);
    }
}
