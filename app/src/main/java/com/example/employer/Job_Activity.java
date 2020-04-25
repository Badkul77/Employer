package com.example.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Job_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText et_job_title,et_job_desc,et_amount,et_booking,et_address,et_minjob;
    Button add_job;
    TextView et_job_start,et_job_end;
    //Spinner et_special;
    CheckBox bp,ws,bs,cs,yes;
    String sstatus;
   TextView et_date;
   ImageButton btndate,btnstart,btnend;
    FirebaseDatabase database;
    DatabaseReference reference;
    String time,date;
    ArrayAdapter<String> adapter;
   // String profesion[] = {"Black Pant", "White Shirt", "Black Shoe ","Clean Save"};
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID,mcompanyname,jobcity,mjoblocation;
    int uid=1000;
    int id=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        //et_special=findViewById(R.id.special);
        //adapter = new ArrayAdapter<String>(Job_Activity.this, android.R.layout.simple_list_item_1, profesion);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
       // et_special.setAdapter(adapter);
        et_job_title=findViewById(R.id.et_job_title);
        et_job_desc=findViewById(R.id.et_desc);
        et_job_start=findViewById(R.id.et_j_start);
        et_job_end=findViewById(R.id.et_j_end);
        et_amount=findViewById(R.id.et_amount);
        et_booking=findViewById(R.id.et_bookingR);
        et_date=findViewById(R.id.et_date);
        et_minjob=findViewById(R.id.et_minjob);
       // et_address=findViewById(R.id.et_address);
        add_job=findViewById(R.id.btn_add_job);
        btndate=findViewById(R.id.btndate);
        btnstart=findViewById(R.id.btnstart);
        btnend=findViewById(R.id.btnend);
        bp=findViewById(R.id.blackpant);
        ws=findViewById(R.id.White);
        bs=findViewById(R.id.blackshoe);
        cs=findViewById(R.id.cleanshave);
        yes=findViewById(R.id.yes);
       /* et_special.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });*/
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        reference=database.getInstance().getReference().child("Jobs").child(userID);
          time= String.valueOf(Calendar.getInstance().getTime());
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);

        /*SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(dateStr);
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
        String newDateStr = postFormater.format(dateObj);*/
        //To find no. of job available
       reference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists())
               {
                   id=(int)dataSnapshot.getChildrenCount()+1;
                   Toast.makeText(Job_Activity.this, ""+id, Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        final DocumentReference documentReference=fStore.collection("Employers").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               if (documentSnapshot.exists())
               {
                   mcompanyname=documentSnapshot.getString("Name_Of_Company");
                   jobcity=documentSnapshot.getString("City");
                   mjoblocation=documentSnapshot.getString("Location");

               }
            }
        }   );
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yes.isChecked())
                {
                    et_booking.setText(mjoblocation);
                }
            }
        });

    btndate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDatePickerDialog();
        }
    });
    btnstart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(Job_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    et_job_start.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }
    });
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Job_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_job_end.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    add_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_job_title.getText().toString().isEmpty()||et_job_desc.getText().toString().isEmpty()||
                        et_job_start.getText().toString().isEmpty()||et_job_end.getText().toString().isEmpty()||et_amount.getText().toString().isEmpty()||
                        et_booking.getText().toString().isEmpty()||et_date.getText().toString().isEmpty()||et_minjob.getText().toString().isEmpty())
                {
                    Toast.makeText(Job_Activity.this, "Fill the required Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                   /* DocumentReference docRef = fStore.collection("Employers").document(userID).collection("Jobs").document("job"+id);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Job_Title",et_job_title.getText().toString());
                    user.put("Job_Desc",et_job_desc.getText().toString());
                    user.put("Job_Special",et_special.getText().toString());
                    user.put("Job_Start_Time",et_job_start.getText().toString());
                    user.put("Job_End_Time",et_job_end.getText().toString());
                    user.put("Job_Amount",et_amount.getText().toString());
                    user.put("Job_Booking_Radius",et_booking.getText().toString());
                    user.put("Job_Date",et_date.getText().toString());

                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Job_Activity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                    final StringBuilder result=new StringBuilder();
                    if(bp.isChecked())
                    {
                        result.append("Black Pant  ");
                    }
                    if(ws.isChecked())
                    {
                        result.append("White Shirt  ");
                    }
                    if(bs.isChecked())
                    {
                        result.append("Black Shoes  ");
                    }
                    if (cs.isChecked())
                    {
                        result.append("Clean Shave  ");
                    }

                    reference.child(time+"job"+id).child("Job_Title").setValue(et_job_title.getText().toString());
                    reference.child(time+"job"+id).child("Job_Desc").setValue(et_job_desc.getText().toString());
                    reference.child(time+"job"+id).child("Job_Special").setValue(result.toString());
                    reference.child(time+"job"+id).child("Job_Start_Time").setValue(et_job_start.getText().toString());
                    reference.child(time+"job"+id).child("Job_End_Time").setValue(et_job_end.getText().toString());
                    reference.child(time+"job"+id).child("Job_Amount").setValue(et_amount.getText().toString());
                    reference.child(time+"job"+id).child("Job_Minimum").setValue(et_minjob.getText().toString());
                    reference.child(time+"job"+id).child("Job_Booking_Radius").setValue(et_booking.getText().toString());
                    reference.child(time+"job"+id).child("Job_Date").setValue(et_date.getText().toString());
                    reference.child(time+"job"+id).child("Company_name").setValue(mcompanyname);
                    reference.child(time+"job"+id).child("UserId").setValue(userID);
                    reference.child(time+"job"+id).child("Location").setValue(mjoblocation);
                    reference.child(time+"job"+id).child("Job_ID").setValue(time+"job"+id);
                    reference.child(time+"job"+id).child("Job_Publish_date").setValue(formattedDate);
                    Toast.makeText(Job_Activity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog=new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
         month+=1;
        String date=""+dayOfMonth+"/"+month+"/"+year;
        et_date.setText(date);
    }
}
