package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    ImageView profile,activeJobs,employeesOnline,employeesRegistered,createJob,menu;
    FragmentTransaction transaction;
    FragmentManager manager;

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
                manager=getSupportFragmentManager();
                transaction=manager.beginTransaction();
                transaction.replace(R.id.ll,new CreateJob());
                transaction.commit();
            }
        });

       
    }


    private void initComponent()
    {
        profile=findViewById(R.id.profieuser);
        activeJobs=findViewById(R.id.activeJobs);
        employeesOnline=findViewById(R.id.employeesOnline);
        employeesRegistered=findViewById(R.id.employeesRegistered);
        createJob=findViewById(R.id.createJob);
       
    }

}
