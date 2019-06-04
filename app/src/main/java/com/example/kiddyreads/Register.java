package com.example.kiddyreads;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kiddyreads.MainActivity;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText ed3,ed4,ed5,ed6;
    Button btn3;

    String serverurl = "http://www.funsproject.com/kiddyread/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed3 = findViewById(R.id.name);
        ed4 = findViewById(R.id.email);
        ed5 = findViewById(R.id.password);
        ed6 = findViewById(R.id.phone);
        btn3 = findViewById(R.id.register);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed3.getText().toString();
                String password = ed5.getText().toString();
                String email = ed4.getText().toString();
                String phone = ed6.getText().toString();
                registerUser(username,password,email,phone);
            }
        });
    }
    private void registerUser(final String username, final String password, final String email, final String phone) {
        class RegisterUser extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("username",username);
                hashMap.put("password",password);
                hashMap.put("email",email);
                hashMap.put("phone",phone);
                RequestHandler rh = new RequestHandler();
                String s;
                s = rh.sendPostRequest(serverurl+"/kiddyread/register.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals("success")){
                    Toast.makeText(Register.this, "Success",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Register.this, "Failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            RegisterUser  ruser = new RegisterUser ();
            ruser.execute();
        }catch(Exception e){}
    }
}
