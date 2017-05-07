package com.application.dorbesh.hospitalofdhaka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class AmbulenceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String[] aNames,aphones;
    ArrayList<Ambulence> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulence);
        recyclerView = (RecyclerView)findViewById(R.id.ambulence_recyclerView);

        aNames = getResources().getStringArray(R.array.ambulenceName);
        aphones = getResources().getStringArray(R.array.ambulencePhn);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8872420019930849/9954850617");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        int i = 0;
        for(String name:aNames){

            Ambulence ambulence = new Ambulence(name,aphones[i]);
            arrayList.add(ambulence);
            i++;
        }
        adapter = new AmbulenceRecyclerAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);
    }
}
