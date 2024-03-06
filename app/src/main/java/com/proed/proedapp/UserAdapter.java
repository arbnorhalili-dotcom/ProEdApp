package com.proed.proedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<User> datasourceUser = new ArrayList<>();
    Context context;

    public UserAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datasourceUser.size();
    }

    @Override
    public Object getItem(int i) {
        return datasourceUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return datasourceUser.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.row_layout,
                    viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.getTvNameSurnameRow().setText(datasourceUser.get(i).getName()+" "+datasourceUser.get(i).getSurname());
        viewHolder.getTvPositionRow().setText(datasourceUser.get(i).getPosition());

        return view;
    }
}
