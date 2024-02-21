package com.proed.proedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;
    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    int UserId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_relative_layout);

        /*tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Mire se erdhet...");
        tvWelcome.setTextColor(getColor(android.R.color.holo_red_dark));
        tvWelcome.setTextSize(50);*/

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(LoginUser(username, password))
                {
                    Toast.makeText(MainActivity.this,
                            "Kredencialet jane ne rregull!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("UserId", UserId);
                    intent.putExtra("Username", username);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            "Kredencialet jane GABIM!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean LoginUser(String username, String password)
    {
        SQLiteDatabase objDb = (new DatabaseHelper(MainActivity.this)).getReadableDatabase();
        Cursor cursor = objDb.query("Users",
                new String[]{"Id,Username,Password"},
                "Username=?",
                new String[]{username},
                "",
                "",
                "");

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String passwordFromDb = cursor.getString(2);
            UserId = cursor.getInt(0);
            if(passwordFromDb.equals(password))
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }
}