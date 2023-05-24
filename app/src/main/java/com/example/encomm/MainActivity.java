package com.example.encomm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText phone;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.ephone);
        password = findViewById(R.id.epass);

        TextView txt = findViewById(R.id.create);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, create.class);
                startActivity(intent);
            }
        });

        Button login1 = findViewById(R.id.login);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                login();

            }
        });


    }

    public void login() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();

        RequestBody phoneRequestBody = RequestBody.create(MediaType.parse("text/plain"), phone.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());
        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), "962");
        Call<UserModel> retCall = service.login(phoneRequestBody, passwordRequestBody,conCodeRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override

            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("loginResponse");
                    editor.apply();
                    Intent intent=new Intent(MainActivity.this,home.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();
            }
        });

    }
}