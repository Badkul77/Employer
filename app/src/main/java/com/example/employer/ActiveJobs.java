package com.example.employer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ActiveJobs extends Fragment
{
    RecyclerView recyclerView;
    JobListAdapter adapter;
    FirebaseFirestore fStore;
    ArrayList<model> al;
    FirebaseDatabase database;
    DatabaseReference reference;
    String jobtitle,companynamem,description,timeofreporting,duratin,date,rupee,userID,job;
    String user,company;
    FirebaseAuth fAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v=inflater.inflate(R.layout.active_jobs_fragment,null);
        /*recyclerView=v.findViewById(R.id.rv);
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        reference=database.getInstance().getReference().child("Jobs").child(userID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        al=new ArrayList<>();
        al.add(new model("Hotel Staff","Stakers","Short desc","9:00",
                "9 Hours","16/03/2020","6000","job0"));
        adapter=new JobListAdapter(getContext(),al);



      /*  final DocumentReference documentReference=fStore.collection("Employers").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    company=documentSnapshot.getString("Name_Of_Company");
                }
            }
        });*/
       /* reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    job = ds.getKey();
                    Log.d("title : ",job);
                    jobtitle=dataSnapshot.child(job).child("Job_Title").getValue().toString();
                    companynamem=dataSnapshot.child(job).child("Company_name").getValue().toString();
                    // comapanynameo.setText(companynamem);
                    //companynamem=company;
                    description=dataSnapshot.child(job).child("Job_Desc").getValue().toString();
                    timeofreporting=dataSnapshot.child(job).child("Job_Start_Time").getValue().toString();
                    duratin="9 Hours";
                    date=dataSnapshot.child(job).child("Job_Date").getValue().toString();
                    rupee=dataSnapshot.child(job).child("Job_Amount").getValue().toString();
                    //  Log.d("title : ",job);
                    //Log.d("title : ",jobtitle);
                    al.add(new model(jobtitle,companynamem,description,timeofreporting,duratin,date,rupee,job));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView.setAdapter(adapter);*/
      return v;
    }
}
