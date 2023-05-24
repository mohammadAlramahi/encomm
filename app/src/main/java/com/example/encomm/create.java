package com.example.encomm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class create extends AppCompatActivity {
    EditText name;
    EditText number;
    EditText email;
    EditText password;
    EditText cpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        TextView btn2 = findViewById(R.id.login);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(create.this, MainActivity.class);
                startActivity(intent);


            }
        });

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.ename);
                number = findViewById(R.id.ephone);
                email = findViewById(R.id.eemail);
                password = findViewById(R.id.epass);
                String namee = name.getText().toString();
                String numberr = number.getText().toString();
                String emaill = email.getText().toString();
                String passwordd = password.getText().toString();
                CheckBox checkBox = findViewById(R.id.check);
                if (checkBox.isChecked()) {

                    if (namee.matches("") || numberr.matches("") || emaill.matches("") || passwordd.matches("") ) {
                        Toast.makeText(create.this, "wrong", Toast.LENGTH_LONG).show();

                    } else {

                        signup();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(create.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 3000);

                    }

                } else {
                    Toast.makeText(create.this, "read privacy policy", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void signup() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Api service = RetrofitClint.getApiService();

        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name.getText().toString());
        RequestBody numberRequestBody = RequestBody.create(MediaType.parse("text/plain"), number.getText().toString());
        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());
        RequestBody conCodeRequestBody = RequestBody.create(MediaType.parse("text/plain"), "962");
        retrofit2.Call<UserModel> retCall = service.SignUp(nameRequestBody,numberRequestBody,emailRequestBody,passwordRequestBody,conCodeRequestBody);
        retCall.enqueue(new Callback<UserModel>() {
            @Override

            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                pd.dismiss();
                if (response.body().isResult()) {
                    Toast.makeText(create.this, "Done", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(create.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}