package com.application.dorbesh.hospitalofdhaka;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.RecyclerViewHoler>{

    ArrayList<DoctorList>arrayList = new ArrayList<>();
    Context ctx;

    DoctorAdapter(ArrayList<DoctorList>arrayList,Context ctx){
        this.ctx = ctx;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlist_layout,parent,false);
        RecyclerViewHoler viewHoler = new RecyclerViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHoler holder, final int position) {

        holder.txtDname.setText(arrayList.get(position).getName());
        holder.txtDexpertise.setText(arrayList.get(position).getExpertise());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phnNumber;

                if(holder.txtDexpertise.getText().equals("24 hour open")){

                    String[] phn = ctx.getResources().getStringArray(R.array.allhospitalsphone);
                    phnNumber = phn[position];

                }else{

                    String[] phn = ctx.getResources().getStringArray(R.array.allDoctorsPhn);
                    phnNumber = phn[position];

                }

               // Toast.makeText(ctx,"click"+phnNumber,Toast.LENGTH_SHORT).show();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode(phnNumber.trim())));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(callIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHoler extends RecyclerView.ViewHolder{


        TextView txtDname,txtDexpertise;
        Button btnCall;

        public RecyclerViewHoler(View v) {
            super(v);

            txtDname = (TextView)v.findViewById(R.id.doctorListName);
            txtDexpertise = (TextView)v.findViewById(R.id.doctorListExpertise);
            btnCall = (Button)v.findViewById(R.id.btnDoctorCall);
        }
    }
}
