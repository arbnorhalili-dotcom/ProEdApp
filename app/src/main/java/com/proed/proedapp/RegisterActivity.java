package com.proed.proedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etTitle, etSurname, etName, etPosition, etCity, etDepartment, etUsername, etPassword;
    CheckBox chbHasChat, chbHasOpenMailAddress;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etTitle = findViewById(R.id.etTitle);
        etSurname = findViewById(R.id.etSurname);
        etName = findViewById(R.id.etName);
        etPosition = findViewById(R.id.etPosition);
        etCity = findViewById(R.id.etCity);
        etDepartment = findViewById(R.id.etDepartment);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        chbHasChat = findViewById(R.id.chbChat);
        chbHasOpenMailAddress = findViewById(R.id.chbMailAddress);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData(etTitle.getText().toString(),etSurname.getText().toString(),etName.getText().toString(),
                        etPosition.getText().toString(),etCity.getText().toString(),etDepartment.getText().toString(),
                        etUsername.getText().toString(), etPassword.getText().toString(),chbHasChat.isChecked(),chbHasOpenMailAddress.isChecked());

            }
        });
    }

    private void SaveData(String title, String surname, String name, String position,
                          String city, String department, String username, String password,
                          boolean hasChat, boolean hasOpenMailAddress)
    {
        SQLiteDatabase objDb = (new DatabaseHelper(RegisterActivity.this)).getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Surname", surname);
        contentValues.put("Name", name);
        contentValues.put("Position", position);
        contentValues.put("City", city);
        contentValues.put("Department", department);
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        contentValues.put("HasChat", hasChat);
        contentValues.put("HasOpenMailAddress", hasOpenMailAddress);

        long result = objDb.insert("Users",null,contentValues);
        if(result>0)
        {
            Toast.makeText(RegisterActivity.this, "Perdoruesi u regjistrua me sukses!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(RegisterActivity.this, "Regjistrimi deshtoi!", Toast.LENGTH_SHORT).show();
        }
    }
}