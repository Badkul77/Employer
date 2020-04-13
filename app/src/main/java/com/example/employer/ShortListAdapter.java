package com.example.employer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShortListAdapter extends RecyclerView.Adapter<ShortListAdapter.ShortListViewHolder> {
    FirebaseDatabase database;
    DatabaseReference reference,reference1,reference2;
    FirebaseAuth fAuth;
    ArrayAdapter<String> adapter;
    String Userid;
    String sstatus;
    Context mCtx;
    String profesion[] = {"Pending Shortlist", "Pending Transport Details", "Pending Start","Live","Completed","Cancelled"};
    ArrayList<ShortListing> modelList;

    public ShortListAdapter(Context mCtx, ArrayList<ShortListing> modelList) {
        this.mCtx = mCtx;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ShortListAdapter.ShortListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCtx).inflate(R.layout.activity_registered,parent,false);
        return new ShortListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShortListAdapter.ShortListViewHolder holder, int position) {
      final ShortListing s=modelList.get(position);
        adapter = new ArrayAdapter<String>(mCtx, android.R.layout.simple_list_item_1, profesion);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        holder.status.setAdapter(adapter);
        holder.name.setText(s.name);
        holder.location.setText(s.location);
        holder.language.setText(s.language);
        holder.current.setText(s.status);
        holder.status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                    {
                        sstatus="Pending Shortlist";
                        break;
                    }
                    case 1:
                    {
                        sstatus="Pending Transport Details";
                        break;
                    }
                    case 2:
                    {
                        sstatus="Pending Start";
                        break;
                    }
                    case 3:
                    {
                        sstatus="Live";
                        break;
                    }
                    case 4:
                    {
                        sstatus="Completed";
                        break;
                    }
                    case 5:
                    {
                        sstatus="Cancelled";
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.set_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth = FirebaseAuth.getInstance();
              final String  userID = fAuth.getCurrentUser().getUid();
              reference1=database.getInstance().getReference().child("Jobs").child(userID).child(s.Uid);

              reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      String start,end,location,amount,title;
                      title=dataSnapshot.child("Job_Title").getValue().toString();
                      start=dataSnapshot.child("Job_Start_Time").getValue().toString();
                      end=dataSnapshot.child("Job_End_Time").getValue().toString();
                      amount=dataSnapshot.child("Job_Amount").getValue().toString();
                      location=dataSnapshot.child("Location").getValue().toString();
                      reference2=database.getInstance().getReference().child("Apply").child(userID).child(s.Uid);
                      reference2.child("job_status").setValue(sstatus);
                      holder.current.setText(sstatus);
                      reference=database.getInstance().getReference().child("Shortlisting").child(s.employee_id).child(s.Uid);
                      reference.child("job_status").setValue(sstatus);
                      reference.child("User_Id").setValue(userID);
                      reference.child("Start").setValue(start);
                      reference.child("End").setValue(end);
                      reference.child("Amount").setValue(amount);
                      reference.child("Location").setValue(location);
                      reference.child("Job_Title").setValue(title);
                      Toast.makeText(mCtx, "Status Succesfully Changed", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ShortListViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,language,current;
        Button set_status;
        Spinner status;
        public ShortListViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Empname);
            location=itemView.findViewById(R.id.location);
            language=itemView.findViewById(R.id.language);
            set_status=itemView.findViewById(R.id.btnsetstatus);
            status=itemView.findViewById(R.id.spinner_status);
            current=itemView.findViewById(R.id.currentss);
        }
    }
}
