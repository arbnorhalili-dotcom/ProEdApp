package com.proed.proedapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView tvUsers;
    ListView lvUsers;
    UserAdapter userAdapter;
    List<User> tempDataSource = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUsers = findViewById(R.id.tvUsers);
        lvUsers = findViewById(R.id.lvUsers);

        userAdapter = new UserAdapter(HomeActivity.this);

        if(getIntent().hasExtra("Username"))
        {
            //lexojme username
        }

        List<String> users = getUsers();
        for(int i=0;i<users.size();i++)
        {
            tvUsers.setText(tvUsers.getText().toString() + "\n" + users.get(i));
        }

        userAdapter.datasourceUser = getUsersList();
        lvUsers.setAdapter(userAdapter);
    }

    private List<String> getUsers()
    {
        List<String> users = new ArrayList<>();

        SQLiteDatabase objDb = (new DatabaseHelper(HomeActivity.this)).getReadableDatabase();
        Cursor cursor = objDb.query("Users",
                new String[]{"Name", "Surname"},
                "",
                new String[]{},
                "",
                "",
                "");

        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            users.add(cursor.getString(0)+" "+cursor.getString(1));
            cursor.moveToNext();
        }

        return users;
    }

    private List<User> getUsersList()
    {
        List<User> users = new ArrayList<>();

        SQLiteDatabase objDb = (new DatabaseHelper(HomeActivity.this)).getReadableDatabase();
        Cursor cursor = objDb.query("Users",
                new String[]{"Id","Name", "Surname", "Position"},
                "",
                new String[]{},
                "",
                "",
                "");

        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            User tempUser = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3));
            users.add(tempUser);
            cursor.moveToNext();
        }

        return users;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemRegisterUser)
        {
            Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.itemTest)
        {
            Toast.makeText(HomeActivity.this, getString(R.string.label_not_implemented), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}