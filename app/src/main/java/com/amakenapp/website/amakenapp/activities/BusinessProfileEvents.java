package com.amakenapp.website.amakenapp.activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.helper.BusinessProfilePlaceOrEventAdapter;
import com.amakenapp.website.amakenapp.helper.BusinessProfilePlaceOrEventListItem;


import java.util.ArrayList;
import java.util.List;


public class BusinessProfileEvents extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<BusinessProfilePlaceOrEventListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.businessProfileEvents_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

            /* every item of  recycler view has a fixed size*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

            /* we put data we want to store inside the list item*/
        listItems = new ArrayList<>();


        for (int i = 0; i <= 10; i++) {
            BusinessProfilePlaceOrEventListItem listItem = new BusinessProfilePlaceOrEventListItem(
                    R.drawable.target,
                    "Event Name " + i ,
                    "Event Category",

                    R.drawable.bookmark,
                    R.drawable.redheart,
                    R.attr.ratingBarStyleSmall,
                    "21",
                    "21",
                    "21"
            );

            listItems.add(listItem);

        }


        adapter = new BusinessProfilePlaceOrEventAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }


    //// TODO: 3/9/2017  get previous fragment (business profile activity) activity not NavDrw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }
        return true;
    }


}
