package com.inyavic.inyavicsage.distresscallsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText usernameInput = findViewById(R.id.username_input);
                        EditText passwordInput = findViewById(R.id.password_input);
                    }
                }
        );
    }
}
