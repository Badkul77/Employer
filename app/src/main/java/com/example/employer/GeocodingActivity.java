package com.example.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class GeocodingActivity extends AppCompatActivity {
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;
    String lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoding);
        addressTV = findViewById(R.id.addressTV);
        latLongTV = findViewById(R.id.latLongTV);
        addressButton = findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.addressET);
                String address = editText.getText().toString();
                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());
                Toast.makeText(GeocodingActivity.this, ""+lat+" "+lon, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    lat= bundle.getString("latitude");
                    lon= bundle.getString("longitude");
                    break;
                default:
                    locationAddress = null;
            }
            latLongTV.setText(locationAddress);
        }
    }
}
