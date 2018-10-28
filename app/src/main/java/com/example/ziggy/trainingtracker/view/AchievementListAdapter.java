package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Achievement;
import com.example.ziggy.trainingtracker.model.IWorkout;

import java.util.ArrayList;
import java.util.List;

public class AchievementListAdapter extends ArrayAdapter<Achievement> {

    private Context mContext;
    private List<Achievement>achievements = new ArrayList<>();

    public AchievementListAdapter(@NonNull Context context, List<Achievement>list){
        super(context, 0, list);
        mContext = context;
        achievements = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_achievement, parent,false);

        Achievement currentAchievement = achievements.get(position);

        TextView achievementNameTextView = listItem.findViewById(R.id.achievementNameTextView);
        TextView progressTextView = listItem.findViewById(R.id.progressTextView);
        ProgressBar progressBar = listItem.findViewById(R.id.progressBar);

        String name = currentAchievement.getName();
        int progress = currentAchievement.getProgress();
        int requirement = currentAchievement.getRequirement();

        achievementNameTextView.setText(name);
        progressTextView.setText(progress + "/" + requirement);
        progressBar.setProgress(100*progress/requirement);

        return listItem;
    }
}
