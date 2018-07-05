package com.inyavic.inyavicsage.distresscallsmanager;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED,
                        Manifest.permission.READ_PHONE_STATE}, 1);

        Intent service = new Intent(this, MyService.class);
        this.startService(service);
    }

    public void loginButtonClicked(View view) {
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (username.equals("admin") && password.equals("adminpass")) {
            i = new Intent(this, DistressCallManagersListActivity.class);
            i.putExtra("username", username);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        } else if (dbHelper.isLoginDetailsCorrect(username, password)) {
            i = new Intent(this, DistressCallInfoListActivity.class);
            i.putExtra("username", username);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }
}
