package com.example.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Detail_Activity extends AppCompatActivity {
    EditText et_f_name, et_l_name, et_email, et_role,
            et_password, et_cnfm_pswd, et_contact, et_altcontact,etaadhar,etnameofcompany,et_typeofbusiness,
            et_company_email,et_city,et_location,et_gstin;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_detail);

        et_f_name=findViewById(R.id.et_f_name);
        et_l_name = findViewById(R.id.et_l_name);
        et_password = findViewById(R.id.et_password);
        et_email=findViewById(R.id.et_email);
        et_role=findViewById(R.id.et_your_role);
        et_contact=findViewById(R.id.et_contact_detail);
        et_altcontact=findViewById(R.id.et_alter_contact_detail);
        etaadhar=findViewById(R.id.et_aadhar);
        etnameofcompany=findViewById(R.id.et_company_name);
        et_typeofbusiness=findViewById(R.id.et_business_type);
        et_company_email=findViewById(R.id.company_email);
        et_city=findViewById(R.id.et_city);
        et_location=findViewById(R.id.et_location);
        et_gstin=findViewById(R.id.et_gst);
        btnRegister=findViewById(R.id.btnRegister);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference=fStore.collection("Employers").document(userID);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_f_name.getText().toString().isEmpty()|| et_l_name.getText().toString().isEmpty() || et_password.getText().toString().isEmpty()
                        || et_company_email.getText().toString().isEmpty()|| et_role.getText().toString().isEmpty() || et_contact.getText().toString().isEmpty()
                        || et_altcontact.getText().toString().isEmpty()|| et_email.getText().toString().isEmpty() || etaadhar.getText().toString().isEmpty()
                        ||etnameofcompany.getText().toString().isEmpty()|| et_typeofbusiness.getText().toString().isEmpty()
                        || et_city.getText().toString().isEmpty()|| et_location.getText().toString().isEmpty()|| et_gstin.getText().toString().isEmpty()
                ){
                    Toast.makeText(Detail_Activity.this, "Fill the required Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference docRef = fStore.collection("Employers").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("first",et_f_name.getText().toString());
                    user.put("last",et_l_name.getText().toString());
                    user.put("email",et_email.getText().toString());
                    user.put("role",et_role.getText().toString());
                    user.put("password",et_password.getText().toString());
                    user.put("contact",et_contact.getText().toString());
                    user.put("Alternate_contact",et_altcontact.getText().toString());
                    user.put("Aadhar_NO",etaadhar.getText().toString());
                    user.put("Name_Of_Company",etnameofcompany.getText().toString());
                    user.put("Type",et_typeofbusiness.getText().toString());
                    user.put("Comapany_Mail",et_company_email.getText().toString());
                    user.put("City",et_city.getText().toString());
                    user.put("Location",et_location.getText().toString());
                    user.put("GSTIN",et_gstin.getText().toString());

                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }
                        }
                    });

                }
            }
        });

    }
}
