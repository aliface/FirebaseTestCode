//THIS IS THE REGISTRATION PAGE BUT I NAMED IT WRONG SO BE VERY CAREFUL WITH USING IT. THE ACTUAL LOGIN PAGE IS NAMED "ActualLogInActivity
package com.example.firebasething;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bRegisterHere;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth = FirebaseAuth.getInstance();



        progressDialog = new ProgressDialog(this);

        bRegisterHere = (Button) findViewById(R.id.bRegisterHere);
        bLogIn = (Button)findViewById(R.id.bLogIn);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);


        bRegisterHere.setOnClickListener(this);
        bLogIn.setOnClickListener(this);

        //if user is already logged in then we can direct them to this page
        if(firebaseAuth.getCurrentUser()!=null) { // means theat the user is already loged in
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

        }

    }


    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            //if username is empty do this
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //if password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if validations are ok then continue
        //we will first show a progressbar

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered/signed in
                            //we will start the profile activity here
                            progressDialog.dismiss();
                            Toast.makeText(LogInActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(LogInActivity.this, "Could not register.Please try again", Toast.LENGTH_SHORT).show();

                        }

                    }

                });
    }

    @Override
    public void onClick(View view) {
        if (view == bRegisterHere) {
            registerUser();

        }

        if (view == bLogIn){
            //will open login activity here
            startActivity(new Intent(this, ActualLogInActivity.class));

        }

    }
}
