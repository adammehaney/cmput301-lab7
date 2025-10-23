package com.example.androiduitesting;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

interface ShowActivityCallback {
    void backButtonClick();
}

public class ShowActivity extends Fragment {
    String text;
    ShowActivityCallback callback;

    public ShowActivity(String text, ShowActivityCallback callback) {
        this.text = text;
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_activity, container, false);

        Button backButton = view.findViewById(R.id.show_activity_back);
        TextView textView = view.findViewById(R.id.city_name_text);

        textView.setText(this.text);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.backButtonClick();
            }
        });

        return view;
    }
}
