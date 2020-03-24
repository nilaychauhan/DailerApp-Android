package com.nilay.dailerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number;
    ImageView dail;
    private static final int REQUEST_CALL = 1;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        dail = findViewById(R.id.dail);
        
        dail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall() {
        String num = number.getText().toString();
        if(num.trim().length() > 0){
            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }else {
                String calling = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(calling)));
            }

        }else {
            Toast.makeText(MainActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
          if(requestCode == REQUEST_CALL){
              if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  makePhoneCall();
              } else {
                  Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
              }
          }
    }
}
