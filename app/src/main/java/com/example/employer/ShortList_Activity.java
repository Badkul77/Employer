package com.example.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ShortList_Activity extends AppCompatActivity {
RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ArrayList<ShortListing> al;
    ShortListAdapter adapter;
    String userID,name,location,language,apply,employee_id,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_list_);
    recyclerView=findViewById(R.id.recyclerview);
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        reference=database.getInstance().getReference().child("Apply").child(userID);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShortList_Activity.this));
        al=new ArrayList<>();
      //  al.add(new ShortListing("Naman","Bavnagar","Hindi","dsfwe"));


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             for(DataSnapshot ds: dataSnapshot.getChildren())
             {
              apply = ds.getKey();
                 Log.d("apply  :",apply);
             name=dataSnapshot.child(apply).child("userName").getValue().toString();
                 Toast.makeText(ShortList_Activity.this, ""+name, Toast.LENGTH_SHORT).show();
                location=dataSnapshot.child(apply).child("userLocation").getValue().toString();
             language=dataSnapshot.child(apply).child("userLangauge").getValue().toString();
            employee_id=dataSnapshot.child(apply).child("employeeId").getValue().toString();
                 status=dataSnapshot.child(apply).child("status").getValue().toString();
             al.add(new ShortListing(name,location,language,apply,employee_id,status));
                 adapter.notifyDataSetChanged();
             }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter=new ShortListAdapter(this,al);
        recyclerView.setAdapter(adapter);
    }

}
