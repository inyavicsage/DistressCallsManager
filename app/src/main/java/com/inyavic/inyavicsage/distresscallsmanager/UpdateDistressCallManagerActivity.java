package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.HashMap;

public class UpdateDistressCallManagerActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    CheckBox passwordCheck;
    Bundle intentData;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_distress_call_manager);

        usernameInput = findViewById(R.id.username_input);

        intentData = getIntent().getExtras();
        String id = intentData.getString("id");
        String username = dbHelper.getDistressCallsManagerUsername(id);
        usernameInput.setText(username);
    }

    public void updateButtonClicked(View view) {
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        passwordCheck = findViewById(R.id.password_check);

        HashMap<String, String> updateMap = new HashMap<>();
        updateMap.put("username", usernameInput.getText().toString());

        if (passwordCheck.isChecked()) {
            updateMap.put("password", passwordInput.getText().toString());
        }

        intentData = getIntent().getExtras();
        String id = intentData.getString("id");
        dbHelper.updateDistressCallsManager(id, updateMap);

        i = new Intent(this, DistressCallManagersListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
