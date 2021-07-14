package com.example.halo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecipientAdapter extends ArrayAdapter<RecipientItem> {

    public RecipientAdapter(Context cxt, ArrayList<RecipientItem> categoryList) {
        super(cxt, 0, categoryList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_recipient_process2, parent, false);
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name2);
        RecipientItem currentItem = getItem(position);
        if (currentItem != null) {
            textViewName.setText(currentItem.getCatName());
        }
        return convertView;
    }
}

