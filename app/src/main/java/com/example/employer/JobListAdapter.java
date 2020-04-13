package com.example.employer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobListViewHolder> {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    String Userid;
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
    public void onBindViewHolder(@NonNull JobListViewHolder holder, final int position) {


        final model m=modelList.get(position);
        holder.etjobTitle.setText(m.getJobtitle());
        holder.etCompantName.setText(m.getCompanynamem());
        holder.etDesciption.setText(m.getDescription());
        holder.etTImeOFReporting.setText(m.getTimeofreporting());
        holder.etDuration.setText(m.getDuratin());
        holder.etDate.setText(m.getDate());
        holder.etRupee.setText(m.getRupee());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, ""+m.getJobtitle()+" "+m.getJob(), Toast.LENGTH_SHORT).show();
                modelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, modelList.size());
                if(m.getJob()!="job0") {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Jobs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(m.getJob())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(mCtx, "Succesfull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(mCtx, " not Succesfull", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(mCtx,EditActivity.class);
                in.putExtra("EXTRA_SESSION_ID",m.job);
                mCtx.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class JobListViewHolder extends RecyclerView.ViewHolder{

        TextView etjobTitle,etCompantName,etDesciption,etTImeOFReporting,etDuration,etDate,etRupee;
        Button delete,edit;

        public JobListViewHolder(@NonNull View itemView) {
            super(itemView);

            etjobTitle=itemView.findViewById(R.id.jobtitle);
            etCompantName=itemView.findViewById(R.id.companyname);
            etDesciption=itemView.findViewById(R.id.description);
            etTImeOFReporting=itemView.findViewById(R.id.timeofreporting);
            etDuration=itemView.findViewById(R.id.duration);
            etDate=itemView.findViewById(R.id.date);
            etRupee=itemView.findViewById(R.id.rupee);
            delete=itemView.findViewById(R.id.btndelete);
            edit=itemView.findViewById(R.id.btnedit);
        }
    }
}
