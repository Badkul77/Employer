package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    TextView txtfirst,txtlast,txtcompanyname,txt_role,txt_phoneno,txt_GSTIN,txt_Aadhar,txt_city,txt_region,txt_company_email,txt_rating,
    txt_logout;
    String mfirst,mlast,mcompanyname,m_role,m_phoneno,m_GSTIN,m_Aadhar,m_city,m_region,m_company_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_employerprofile);

        firebaseAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        txtfirst=findViewById(R.id.tv_firstname);
        txtlast=findViewById(R.id.tv_lastname);
        txtcompanyname=findViewById(R.id.tv_companyname);
        txt_role=findViewById(R.id.tv_role);
        txt_phoneno=findViewById(R.id.tv_phonenumber);
        txt_GSTIN=findViewById(R.id.tv_company_Gstin);
        txt_Aadhar=findViewById(R.id.tv_aadhar);
        txt_city=findViewById(R.id.tv_cityname);
        txt_region=findViewById(R.id.tv_region);
        txt_company_email=findViewById(R.id.tv_company_email);
        txt_rating=findViewById(R.id.tvRating);
        txt_logout=findViewById(R.id.tv_logout);
        if (firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(getApplicationContext(),RegisterAcitivity.class));
            finish();
        }
        else
        {

            final DocumentReference docRef= fStore.collection("Employers").document(firebaseAuth.getCurrentUser().getUid());
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                  if (documentSnapshot.exists())
                  {
                   mfirst=documentSnapshot.getString("first");
                   mlast=documentSnapshot.getString("last");
                   mcompanyname=documentSnapshot.getString("Name_Of_Company");
                   m_role=documentSnapshot.getString("role");
                   m_phoneno=documentSnapshot.getString("contact");
                   m_GSTIN=documentSnapshot.getString("GSTIN");
                   m_Aadhar=documentSnapshot.getString("Aadhar_NO");
                   m_city=documentSnapshot.getString("City");
                   m_region=documentSnapshot.getString("Location");
                   m_company_email=documentSnapshot.getString("Comapany_Mail");

                   txtfirst.setText(mfirst);
                   txtlast.setText(mlast);
                   txtcompanyname.setText(mcompanyname);
                   txt_role.setText(m_role);
                   txt_phoneno.setText(m_phoneno);
                   txt_GSTIN.setText(m_GSTIN);
                   txt_Aadhar.setText(m_Aadhar);
                   txt_city.setText(m_city);
                   txt_region.setText(m_region);
                   txt_company_email.setText(m_company_email);
                   txt_rating.setText("4.5");
                  }
                  else
                  {
                      Log.d(TAG, "Retrieving Data: Profile Data Not Found ");
                  }

                }
            });
        }
txt_logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),RegisterAcitivity.class));
        finish();
    }
});
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

}
