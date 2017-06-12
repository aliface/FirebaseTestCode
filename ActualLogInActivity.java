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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActualLogInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvSignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_log_in);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        bLogin = (Button)findViewById(R.id.bLogin);
        tvSignUp = (TextView)findViewById(R.id.tvSignUp);

        bLogin.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

    if(firebaseAuth.getCurrentUser()!=null) { // means theat the user is already loged in
        //profile activity
        finish();
        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

    }



    }

    private void userLogin(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //if username is empty do this
            Toast.makeText(this, "Please enter username/email", Toast.LENGTH_SHORT).show();
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

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //this will be implemented once the signin is complete
                        progressDialog.dismiss();

                        if(task.isSuccessful()) {
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        }
                    }
                });



    }


    @Override
    public void onClick(View v) {
        if(v == bLogin){
           userLogin();
        }
        if(v == tvSignUp){
            finish();
            startActivity(new Intent(this, LogInActivity.class));

        }

    }
}
