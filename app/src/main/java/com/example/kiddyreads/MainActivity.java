package com.example.kiddyreads;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn1,btn2;
    String serverurl = "http://www.funsproject.com/kiddyread/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                loginUser(username,password);
            }
        });
    }

    private void loginUser(final String username, final String password) {
        class LoginUser extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("username",username);
                hashMap.put("password",password);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(serverurl+"/kiddyread/login.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals("success")){
                    Toast.makeText(MainActivity.this, "Success",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            LoginUser luser = new LoginUser();
            luser.execute();
        }catch(Exception e){}
    }


}
