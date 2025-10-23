package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare the variables so that you will be able to reference it later.
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CityListFragment cityListFragment = new CityListFragment(cityAdapter, new CityListFragmentCallback() {
            @Override
            public void onItemClick(int i) {
                String cityName = cityAdapter.getItem(i);

                FragmentTransaction itemFragmentTransaction = fragmentManager.beginTransaction();

                ShowActivity showActivity = new ShowActivity(cityName, new ShowActivityCallback() {
                    @Override
                    public void backButtonClick() {
                        fragmentManager.popBackStack();
                    }
                });


                itemFragmentTransaction.replace(R.id.fragment_container, showActivity);
                itemFragmentTransaction.addToBackStack("SecondFragment");
                itemFragmentTransaction.commit();
            }
        });

        fragmentTransaction.add(R.id.fragment_container, cityListFragment);
        fragmentTransaction.commit();
    }
}