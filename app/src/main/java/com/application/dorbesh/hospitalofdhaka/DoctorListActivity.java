package com.application.dorbesh.hospitalofdhaka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class DoctorListActivity extends AppCompatActivity {

    String[] dName,dexpertise,dphone;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DoctorList>arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        recyclerView = (RecyclerView)findViewById(R.id.doctorlistrecyclerview);

        dName = getResources().getStringArray(R.array.allDoctorsName);
        dexpertise = getResources().getStringArray(R.array.allDoctorsExpertise);
        dphone = getResources().getStringArray(R.array.allDoctorsPhn);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8872420019930849/9954850617");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        int i = 0;

        for(String name:dName){
            arrayList.add(new DoctorList(name,dexpertise[i],dphone[i]));
            i++;
        }
        adapter = new DoctorAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
    }
}
