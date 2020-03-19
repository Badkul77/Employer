package com.example.employer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import pl.droidsonroids.gif.GifImageView;

public class Splashscreen extends Activity {


    GifImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        image=findViewById(R.id.splash_screen);
        new MyThread().start();
    }

    class MyThread extends Thread
    {
        @Override
        public void run()
        {
            super.run();
            try {
                Thread.sleep(6000);
                Intent in=new Intent(Splashscreen.this, RegisterAcitivity.class);
                startActivity(in);
                finish();
            }
            catch (Exception e)
            {

            }
        }
    }
}
