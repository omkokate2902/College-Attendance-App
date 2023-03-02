package com.omkokate.attendance;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final int textSize;
    private final String[] items;

    public CustomSpinnerAdapter(Context context, int textSize, int textViewResourceId, String[] items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.textSize = textSize;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return view;
    }
}
