package com.application.dorbesh.hospitalofdhaka;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import nearby.MapsActivity;

public class MainActivity extends AppCompatActivity {

    Button button;

    FloatingActionButton fabPlus,fabAmbulence,fabInfo,fabHospital;
    Animation fabOpen,fabClose,fabRClockwise,fabRantiClockWise;
    boolean isOpen = false;

    AutoCompleteTextView textView;

    ArrayList<Hospital> arrayList = new ArrayList<>();
    String[] name,status,phone,open;
    int[] img = {R.drawable.unitedhospital,R.drawable.labaiddhanmondi,R.drawable.alhelalhospital,R.drawable.birdem,R.drawable.japan,R.drawable.apollo,
    R.drawable.uttara,R.drawable.central,R.drawable.sprc,R.drawable.islami,
            R.drawable.health,R.drawable.advanceeye,R.drawable.ahmedmedical,
            R.drawable.ahsaniamissioncencer,R.drawable.archihospital,
            R.drawable.alashraf,R.drawable.alarafaclinic,R.drawable.albarakhospital,
            R.drawable.almadinahospital,R.drawable.almarkazulhospital,R.drawable.almohithospital,
            R.drawable.shaheedsuhrawadyhospital,R.drawable.mirpuradunikhospital,R.drawable.alhelalhospital,
            R.drawable.aljavalenurhospital,R.drawable.alrajhihospital,R.drawable.albirunihospital,
            R.drawable.alfatehhospital,R.drawable.arogyaniketonhospital,R.drawable.ayshamemorialhospital,
            R.drawable.bdfhospital,R.drawable.bangabanduhospital,R.drawable.banglanursinghospital,
            R.drawable.nationalhospital,R.drawable.bdmhospital,R.drawable.lifecare,
            R.drawable.anwarmodernhospital,R.drawable.greenlife,R.drawable.armforce,R.drawable.populardhanmondi,
            R.drawable.ibnsina,R.drawable.medinova,R.drawable.comfort,R.drawable.kidneyandurology,
            R.drawable.digilab,R.drawable.lions,R.drawable.cityhospital,R.drawable.somitrahospital
    };
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (AutoCompleteTextView)findViewById(R.id.searchHospital);
        button = (Button)findViewById(R.id.btnNearby);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }else{
                    if (checkLocationPermission()) {
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                    }
                }
                }
        });


        //ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8872420019930849/9954850617");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        fabPlus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fabAmbulence = (FloatingActionButton)findViewById(R.id.fab_ambulence);
        fabInfo = (FloatingActionButton)findViewById(R.id.fab_info);
        fabHospital = (FloatingActionButton)findViewById(R.id.fab_hospital);
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabRantiClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlock);



        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpen){

                    fabAmbulence.startAnimation(fabClose);
                    fabInfo.startAnimation(fabClose);
                    fabHospital.startAnimation(fabClose);
                    fabPlus.startAnimation(fabRantiClockWise);
                    fabInfo.setClickable(false);
                    fabAmbulence.setClickable(false);
                    fabHospital.setClickable(false);
                    isOpen =false;

                }else{

                    fabAmbulence.startAnimation(fabOpen);
                    fabInfo.startAnimation(fabOpen);
                    fabHospital.startAnimation(fabOpen);
                    fabPlus.startAnimation(fabRClockwise);
                    fabInfo.setClickable(true);
                    fabAmbulence.setClickable(true);
                    fabHospital.setClickable(true);
                    isOpen =true;
                }

            }
        });


        fabAmbulence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AmbulenceActivity.class));
            }
        });

        fabHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HospitalListActivity.class));
            }
        });


        fabInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DoctorListActivity.class));
            }
        });

        name = getResources().getStringArray(R.array.h_name);
        status = getResources().getStringArray(R.array.h_status);
        phone = getResources().getStringArray(R.array.h_phone);
        open = getResources().getStringArray(R.array.h_open);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        int i = 0;

        for(String Name:name){

            arrayList.add(new Hospital(Name,status[i],open[i],phone[i],img[i]));
            i++;

        }
        adapter = new RecyclerViewAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

        ArrayAdapter<String> autoTextAdapter = new ArrayAdapter<String>(this,R.layout.auto_list_row,R.id.autoCtxt,name);
        textView.setAdapter(autoTextAdapter);
        final String[] lat = getResources().getStringArray(R.array.latitude);
        final String[] lng = getResources().getStringArray(R.array.longitude);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i;
                String hname= parent.getAdapter().getItem(position).toString();
                for(i = 0 ;i < name.length;i++){
                    if(name[i].equalsIgnoreCase(hname)){
                        break;
                    }
                }
                Intent intent = new Intent(MainActivity.this,HospitalClick.class);
                intent.putExtra("lat",lat[i]);
                intent.putExtra("lng",lng[i]);
                intent.putExtra("H_Name",hname);
                textView.setText("");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {


                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Toast.makeText(this, "permission allow", Toast.LENGTH_SHORT).show();

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
