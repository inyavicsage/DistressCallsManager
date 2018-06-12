package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
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
    }

    public void loginButtonClicked(View view) {
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        if (usernameInput.getText().toString().equals("admin") && passwordInput.getText().toString().equals("adminpass")) {
            i = new Intent(this, DistressCallManagersListActivity.class);
            i.putExtra("username", usernameInput.getText().toString());
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        } else if (dbHelper.isLoginDetailsCorrect(
                usernameInput.getText().toString(), passwordInput.getText().toString())) {
            i = new Intent(this, DistressCallInfoListActivity.class);
            i.putExtra("username", usernameInput.getText().toString());
            startActivity(i);
            finish();
        }
    }
}
