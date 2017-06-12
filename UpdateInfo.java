package com.example.firebasething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateInfo extends AppCompatActivity {

    private EditText etName,etCarType, etLicensePlate;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        
        etName = (EditText)findViewById(R.id.etName);
        etCarType = (EditText)findViewById(R.id.etCarType);
        etLicensePlate = (EditText)findViewById(R.id.etPlate);
        
    }
}
