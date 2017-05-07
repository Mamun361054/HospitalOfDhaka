package com.application.dorbesh.hospitalofdhaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class HospitalListActivity extends AppCompatActivity {

    String[] Hname,Hphone;
    String open;
    ArrayList<DoctorList>arrayList = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        recyclerView = (RecyclerView)findViewById(R.id.hospitallistRecycler);

        //ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8872420019930849/9954850617");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Hname = getResources().getStringArray(R.array.allHospitalsName);
        Hphone = getResources().getStringArray(R.array.allhospitalsphone);
        open = "24 hour open";

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        int i = 0;
        for(String hname:Hname){

            arrayList.add(new DoctorList(hname,open,Hphone[i]));
            i++;
        }
        adapter = new DoctorAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
    }
}
