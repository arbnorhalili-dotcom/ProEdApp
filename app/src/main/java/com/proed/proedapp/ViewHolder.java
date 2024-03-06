package com.proed.proedapp;

import android.view.View;
import android.widget.TextView;

public class ViewHolder {
    TextView tvNameSurnameRow, tvPositionRow;

    public ViewHolder(View view)
    {
        tvNameSurnameRow = view.findViewById(R.id.tvNameSurnameRow);
        tvPositionRow = view.findViewById(R.id.tvPositionRow);
    }

    public TextView getTvNameSurnameRow() {
        return tvNameSurnameRow;
    }

    public TextView getTvPositionRow() {
        return  tvPositionRow;
    }
}
