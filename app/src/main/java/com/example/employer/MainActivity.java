package com.example.employer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    JobListAdapter adapter;
    FirebaseFirestore fStore;
    ArrayList<model> al;
    FirebaseDatabase database;
    DatabaseReference reference;
    String jobtitle,companynamem,description,timeofreporting,duratin,date,rupee,userID,job;
    String user,company;
    FirebaseAuth fAuth;
    ImageView profile,activeJobs,employeesOnline,employeesRegistered,createJob,menu;
    FragmentTransaction transaction;
    FragmentManager manager;
    TextView comapanynameo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        initComponent();
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.ll,new ActiveJobs());
        transaction.commit();

        activeJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to active jobs fragment
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                transaction.replace(R.id.ll,new ActiveJobs());
                transaction.commit();

            }
        });
        employeesRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to employees Rehistered fragment
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                transaction.replace(R.id.ll,new EmployeesOnline());
                transaction.commit();
            }
        });
        employeesOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to employees online fragment
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                transaction.replace(R.id.ll,new EmployeesRegistered());
                transaction.commit();
            }
        });
        createJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to create job fragment
               /* manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                transaction.replace(R.id.ll,new CreateJob());
                transaction.commit();*/
                startActivity(new Intent(getApplicationContext(),Job_Activity.class));
                finish();

            }
        });


        //recyclerView=findViewById(R.id.recyclerview);
       comapanynameo=findViewById(R.id.companyname);
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        reference=database.getInstance().getReference().child("Jobs").child(userID);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //al=new ArrayList<>();
        //al.add(new model("Hotel Staff","Stakers","Short desc","9:00",
             //   "9 Hours","16/03/2020","6000"));
        //adapter=new JobListAdapter(MainActivity.this,al);



        final DocumentReference documentReference=fStore.collection("Employers").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    company=documentSnapshot.getString("Name_Of_Company");
                    comapanynameo.setText(company);
                }
            }
        });
        /*reference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    al.add(new model(jobtitle,companynamem,description,timeofreporting,duratin,date,rupee));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
     /*   reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   // job = ds.getKey();
                   // Log.d("title : ",job);
                    jobtitle=dataSnapshot.child("Job_Title").getValue().toString();

                    //   companynamem=dataSnapshot.child("job").child("Company_name").getValue().toString();
                    // companynamem="Demo";
                  //  description=dataSnapshot.child("Job_Desc").getValue().toString();
                    timeofreporting=dataSnapshot.child("Job_Start_Time").getValue().toString();
                    duratin="9 Hours";
                    date=dataSnapshot.child("Job_Date").getValue().toString();
                    rupee=dataSnapshot.child("Job_Amount").getValue().toString();
                    //  Log.d("title : ",job);
                    //Log.d("title : ",jobtitle);


                    al.add(new model(jobtitle,companynamem,description,timeofreporting,duratin,date,rupee));
                    adapter.notifyDataSetChanged();




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                jobtitle=dataSnapshot.child("Job_Title").getValue().toString();

                // companynamem=dataSnapshot.child("job").child("Company_name").getValue().toString();
                // companynamem="Demo";
                description=dataSnapshot.child("Job_Desc").getValue().toString();
                timeofreporting=dataSnapshot.child("Job_Start_Time").getValue().toString();
                duratin="9 Hours";
                date=dataSnapshot.child("Job_Date").getValue().toString();
                rupee=dataSnapshot.child("Job_Amount").getValue().toString();
                //Log.d("title : ",job);
                //Log.d("title : ",jobtitle);
                al.add(new model(jobtitle,companynamem,description,timeofreporting,duratin,date,rupee));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
      /*  reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    job = ds.getKey();
                    Log.d("title : ",job);
                    jobtitle=dataSnapshot.child(job).child("Job_Title").getValue().toString();
               //   companynamem=dataSnapshot.child("job").child("Company_name").getValue().toString();
                 // companynamem="Demo";
                    description=dataSnapshot.child(job).child("Job_Desc").getValue().toString();
                    timeofreporting=dataSnapshot.child(job).child("Job_Start_Time").getValue().toString();
                    duratin="9 Hours";
                    date=dataSnapshot.child(job).child("Job_Date").getValue().toString();
                    rupee=dataSnapshot.child(job).child("Job_Amount").getValue().toString();
                  //  Log.d("title : ",job);
                    //Log.d("title : ",jobtitle);
                    al.add(new model(jobtitle,companynamem,description,timeofreporting,duratin,date,rupee));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        //recyclerView.setAdapter(adapter);

    }
    private void initComponent()
    {
        profile=findViewById(R.id.imageView2);
        activeJobs=findViewById(R.id.activeJobs);
        employeesOnline=findViewById(R.id.employeesOnline);
        employeesRegistered=findViewById(R.id.employeesRegistered);
        createJob=findViewById(R.id.createJob);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),RegisterAcitivity.class));
            finish();
        }
        else if(item.getItemId()==R.id.profile)
        {
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            finish();
        }
        else if (item.getItemId()==R.id.add_job)
        {
            startActivity(new Intent(getApplicationContext(),Job_Activity.class));
            finish();
        }
        return true;
    }
}
