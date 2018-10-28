package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Achievement;
import com.example.ziggy.trainingtracker.model.IChallenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeListAdapter extends ArrayAdapter<IChallenge> {

    private Context mContext;
    private List<IChallenge>challenges = new ArrayList<>();
    private NavigationManager navigator;

    ChallengeListAdapter(@NonNull Context context, List<IChallenge>list, NavigationManager navigator){
        super(context, 0, list);
        mContext = context;
        challenges = list;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_challenge, parent,false);

        IChallenge currentChallenge = challenges.get(position);

        TextView challengeNameTextView = listItem.findViewById(R.id.challengeNameTextView);
        TextView challengeScoreTextView = listItem.findViewById(R.id.challengeScoreTextView);
        Button startChallengeButton = listItem.findViewById(R.id.startChallengeButton);

        challengeNameTextView.setText(currentChallenge.getName());
        challengeScoreTextView.setText(String.valueOf(currentChallenge.getScore()));

        startChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateActiveChallenge(currentChallenge);
            }
        });

        return listItem;
    }
}
