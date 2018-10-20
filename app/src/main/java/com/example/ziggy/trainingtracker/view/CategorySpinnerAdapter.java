package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;

import com.example.ziggy.trainingtracker.R;

public class CategorySpinnerAdapter extends ArrayAdapter<CategorySpinnerObject> {

    private Context mContext;
    private List<CategorySpinnerObject> categories;
    private boolean isFromView = false;

    public CategorySpinnerAdapter(Context context, int resource, List<CategorySpinnerObject> categories) {
        super(context, resource, categories);
        this.mContext = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return customSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customSpinnerView(position, convertView, parent);
    }

    /**
     * The custom spinner view containing both a TextView and a checkbox
     * @param position Position of the current object in the spinners array of objects
     * @param convertView the view holding the custom_spinner_item layout
     * @param parent defines the parent view group which contains this view
     * @return The modified convertView now using the custom spinner layout
     * and containing a holder for holding the name and remembering the checkbox status
     */
    private View customSpinnerView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.category_spinner_item, parent,false);
            holder = new ViewHolder();
            holder.mCategoryName = convertView.findViewById(R.id.categoryTextView);
            holder.mCategoryCheckBox = convertView.findViewById(R.id.categoryCheckbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mCategoryName.setText(categories.get(position).getCategoryName());

        isFromView = true;
        holder.mCategoryCheckBox.setChecked(categories.get(position).isCategorySelected());
        isFromView = false;


        if ((position == 0)) {
            holder.mCategoryCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCategoryCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCategoryCheckBox.setTag(position);
        holder.mCategoryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isFromView) {
                    categories.get(position).setCategorySelected(isChecked);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView mCategoryName;
        private CheckBox mCategoryCheckBox;
    }
}
