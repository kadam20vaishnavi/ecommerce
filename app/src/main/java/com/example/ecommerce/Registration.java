package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    EditText name,mobile,address,email,password,cpass;
    Button button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration3);

        initViews();
        onClickListener();

    }

    private void onClickListener() {

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().length()==0){
                    name.setError("name not entered");
                    name.requestFocus();
                }
                else if(mobile.getText().toString().length() >=11){
                    mobile.setError("please enter 10 digit mobile");
                    mobile.requestFocus();
                }
                else if(!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Valid email:abc20@gmail.com");
                    email.requestFocus();
                }
                else if(password.getText().toString().length()== 0){
                    password.setError("password required");
                    password.requestFocus();
                }
                else if(cpass.getText().toString().length()==0){
                    cpass.setError("Please confirm password");
                    cpass.requestFocus();
                }
                else if(!password.getText().toString().equals(cpass.getText().toString())){
                    cpass.setError("Password Not matched");
                    cpass.requestFocus();
                }
                else if(password.getText().toString().length()<8 ){
                    password.setError("Password should be atleast of 8 charactors");
                password.requestFocus();
            }
            else {
                    /*Intent intent = new Intent(Registration.this, Login.class);
                    intent.putExtra("name", ""+name.getText().toString());
                    intent.putExtra("mobileNumber", ""+mobile.getText().toString());
                    intent.putExtra("address", ""+address.getText().toString());
                    intent.putExtra("email", ""+email.getText().toString());
                    intent.putExtra("password", ""+password.getText().toString());
                    startActivity(intent);*/

                    onSubmit();
            }

            }
        });

    }

        private void initViews() {
            name = findViewById(R.id.name);
            mobile =findViewById(R.id.mobile);
            address = findViewById(R.id.address);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            cpass = findViewById(R.id.cpass);
            button = findViewById(R.id.submit);

        }
        private void onSubmit(){
            String Name,Mobile,Email,Password,Address;

            Name=name.getText().toString();
            Mobile=mobile.getText().toString();
            Email=email.getText().toString();
            Password=password.getText().toString();
            Address=address.getText().toString();
           // email1 = email.getText().toString().trim();

            Call<Registerresponce> call = Api.getApi().create(""+Name,""+Mobile,""+Address,""+Email,""+Password);
            call.enqueue(new Callback<Registerresponce>() {
                @Override
                public void onResponse(Call<Registerresponce> call, Response<Registerresponce> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("true")) {

                            Intent intent=new Intent(Registration.this,Login.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();

                        }
                    }

                }

                @Override
                public void onFailure(Call<Registerresponce> call, Throwable t) {
                    Log.e("registerError", "" + t.getMessage());
                }
            });
        }
}
