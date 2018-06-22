package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddDistressCallManagerActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText passwordInput;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);
    Bundle intentData;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distress_call_manager);

        intentData = getIntent().getExtras();
    }

    public void addButtonClicked(View view) {
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        DistressCallsManagers DCM = new DistressCallsManagers(usernameInput.getText().toString(),
                passwordInput.getText().toString());
        dbHelper.addDistressCallsManager(DCM);

        i = new Intent(this, DistressCallManagersListActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
