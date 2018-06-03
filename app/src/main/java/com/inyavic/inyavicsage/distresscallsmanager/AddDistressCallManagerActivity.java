package com.inyavic.inyavicsage.distresscallsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class AddDistressCallManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distress_call_manager);

        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText usernameInput = findViewById(R.id.username_input);
                        EditText passwordInput = findViewById(R.id.password_input);
                    }
                }
        );
    }
}
