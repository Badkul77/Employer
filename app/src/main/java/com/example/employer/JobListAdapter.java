package com.example.employer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobListViewHolder> {

    Context mCtx;
    ArrayList<model> modelList;

    public JobListAdapter(Context mCtx, ArrayList<model> modelList) {
        this.mCtx = mCtx;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public JobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mCtx).inflate(R.layout.item_active_job_list_view,parent,false);
        return new JobListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, int position) {

        model m=modelList.get(position);

        holder.etjobTitle.setText(m.getJobtitle());
        holder.etCompantName.setText(m.getCompanynamem());
        holder.etDesciption.setText(m.getDescription());
        holder.etTImeOFReporting.setText(m.getTimeofreporting());
        holder.etDuration.setText(m.getDuratin());
        holder.etDate.setText(m.getDate());
        holder.etRupee.setText(m.getRupee());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class JobListViewHolder extends RecyclerView.ViewHolder{

        TextView etjobTitle,etCompantName,etDesciption,etTImeOFReporting,etDuration,etDate,etRupee;

        public JobListViewHolder(@NonNull View itemView) {
            super(itemView);

            etjobTitle=itemView.findViewById(R.id.jobtitle);
            etCompantName=itemView.findViewById(R.id.companyname);
            etDesciption=itemView.findViewById(R.id.description);
            etTImeOFReporting=itemView.findViewById(R.id.timeofreporting);
            etDuration=itemView.findViewById(R.id.duration);
            etDate=itemView.findViewById(R.id.date);
            etRupee=itemView.findViewById(R.id.rupee);

        }
    }
}
