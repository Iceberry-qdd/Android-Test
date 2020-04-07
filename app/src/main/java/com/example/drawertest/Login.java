package com.example.drawertest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login);//登录按钮
        final EditText username = findViewById(R.id.Username);
        final EditText password = findViewById(R.id.password);
        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() != 0 && password.getText().toString().length() != 0) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String un = username.getText().toString();
                            String pw = password.getText().toString();
                            Users user = new Users();
                            List<Users> tempUsers = DataSupport.findAll(Users.class);
                            for (int i = 0; i < tempUsers.size(); i++) {
                                if (tempUsers.get(i).getUsername().equals(un) && tempUsers.get(i).getPassword().equals(pw)) {
                                    DataSupport.deleteAll(Users.class, "username=?", un);
                                    break;
                                }
                            }
                            user.setUsername(un);
                            user.setPassword(pw);
                            user.setStatus(true);
                            user.save();

                            finishActivity(1);
                            Intent intent=new Intent();
                            startActivity(intent);
                            finish();
                        }
                    }).start();

                } else {
                    Toast.makeText(Login.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
