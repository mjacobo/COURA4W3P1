package com.mj.courseraprw3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mj.courseraprw3.Adapter.MyAdapter;
import com.mj.courseraprw3.db.BaseDatos;
import com.mj.courseraprw3.pojo.pets;

import java.util.ArrayList;

public class top_five extends AppCompatActivity {
    ArrayList<pets> myPetList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBarTopFive);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setData(this.getBaseContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.rcvMyCarrouselTopFive);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myPetList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    void setData (Context context) {
        BaseDatos db = new BaseDatos(context);
        myPetList = new ArrayList<>();
        
        myPetList = db.getMyPetList();
        
        if(myPetList.size() == 0){
            Toast.makeText(context, "No hay likes registrados", Toast.LENGTH_SHORT).show();
        }
    }

}
