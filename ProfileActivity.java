package com.example.firebasething;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView tvUserEmail;
    private Button bLogOut;

    private Button bUpdateInfo;

    private DatabaseReference databaseReference;

    private EditText etName, etCar;
    private Button bSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUserEmail = (TextView)findViewById(R.id.tvUserEmail);
        bLogOut =(Button)findViewById(R.id.bLogOut);

        firebaseAuth = FirebaseAuth.getInstance();

        bUpdateInfo = (Button)findViewById(R.id.bUpdateInfo);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        etName = (EditText)findViewById(R.id.etName);
        etCar = (EditText)findViewById(R.id.etCar);

        bSave = (Button)findViewById(R.id.bSave);



        if(firebaseAuth.getCurrentUser() == null){
            //means user is not logged in
            startActivity(new Intent(this, ActualLogInActivity.class));
            finish();
        }

        FirebaseUser user = firebaseAuth.getCurrentUser(); //get users information i.e. email nad password

        tvUserEmail.setText("Welcome "+ user.getEmail());



        //Setting Onclick listeners for every view we use
        bUpdateInfo.setOnClickListener(this);
        bSave.setOnClickListener(this);
        bLogOut.setOnClickListener(this);
    }

    private void saveUserInformation(){

        String name = etName.getText().toString().trim();
        String CarType = etCar.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, CarType);

        FirebaseUser user = firebaseAuth.getCurrentUser(); // need to get the unique ID for the user

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved", Toast.LENGTH_LONG).show();


    }




    @Override
    public void onClick(View v) {

        if(v == bLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, ActualLogInActivity.class));
        }
        if(v == bUpdateInfo) {
            startActivity(new Intent(this, UpdateInfo.class));
        }

        if(v == bSave){
            saveUserInformation();
        }

    }




}
