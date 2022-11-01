package com.example.ecommerce;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText password;
    TextInputEditText email;
    TextView register;
    Button button;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        register = findViewById(R.id.link);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        try{

            if (intent!=null) {
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Loginresponce> call = Api.getApi().login(email.getText().toString(), password.getText().toString());

                Log.e("in","after api");
                call.enqueue(new Callback<Loginresponce>() {
                    @Override
                    public void onResponse(Call<Loginresponce> call, Response<Loginresponce> response) {

                        if (response.isSuccessful()) {
                            Log.e("in","in successfull");
                            id = String.valueOf(response.body().getId());
                            if (response.body().getSuccess().equalsIgnoreCase("true")) {

                                Log.e("in","in equal ignore");
                                SharedPreferences.Editor editor = getSharedPreferences("LoginInfo", MODE_PRIVATE).edit();
                                editor.putString("userId",id) ;
                                editor.putString("email",email.getText().toString());
                                editor.putString("password",password.getText().toString());
                                editor.commit();

                                Intent intent=new Intent(Login.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast toast=Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);


                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                                Toast toast=Toast.makeText(getApplicationContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT);
//                                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
//                                toast.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Loginresponce> call, Throwable t) {
                        Log.e("userListError", ""+t.getMessage());

                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();//exit the app
    }

    private void initViews() {

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.login);

    }
}