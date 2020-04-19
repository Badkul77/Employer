package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Verification_Activity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button btnrefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        btnrefresh=findViewById(R.id.btnrefresh);
        final DocumentReference documentReference=fStore.collection("Employers").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    String check=documentSnapshot.getString("Valid").trim();
                    if (check.contains("true"))
                    {
                        Intent intent=new Intent(Verification_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }  );
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DocumentReference documentReference=fStore.collection("Employers").document(userID);
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String check=documentSnapshot.getString("Valid").trim();
                            if (check.contains("true"))
                            {
                                Intent intent=new Intent(Verification_Activity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Verification_Activity.this, "You are not verified now", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }  );
            }
        });


    }
}
