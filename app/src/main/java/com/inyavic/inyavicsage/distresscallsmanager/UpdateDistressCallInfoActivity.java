package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDistressCallInfoActivity extends AppCompatActivity {

    Spinner typeInput;
    TextView priorityInput;
    EditText callerNameInput;
    TextView callerPhoneNumInput;
    EditText callerLocInput;
    EditText descriptionInput;
    TextView recCallPathInput;
    TextView datetimeReceivedInput;
    Bundle intentData;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_distress_call_info);

        intentData = getIntent().getExtras();
        typeInput = findViewById(R.id.type_input);
        priorityInput = findViewById(R.id.priority_input);
        callerNameInput = findViewById(R.id.caller_name_input);
        callerPhoneNumInput = findViewById(R.id.caller_phone_num_input);
        callerLocInput = findViewById(R.id.caller_loc_input);
        descriptionInput = findViewById(R.id.description_input);
        recCallPathInput = findViewById(R.id.rec_call_path_input);
        datetimeReceivedInput = findViewById(R.id.datetime_received_input);

        int id = intentData.getInt("id");
        ArrayList<DistressCallsInfo> DCI = dbHelper.getDistressCallsInfo(id);

        typeInput.setSelection(2);
        priorityInput.setText(DCI.get(0).getPriority());
        callerNameInput.setText(DCI.get(0).getCallerName());
        callerPhoneNumInput.setText(DCI.get(0).getCallerPhoneNum());
        callerLocInput.setText(DCI.get(0).getCallerLoc());
        descriptionInput.setText(DCI.get(0).getDescription());
        recCallPathInput.setText(DCI.get(0).getRecCallPath());
        datetimeReceivedInput.setText(DCI.get(0).getDatetimeReceived());
    }

    public void updateButtonClicked(View view) {
        typeInput = findViewById(R.id.type_input);
        priorityInput = findViewById(R.id.priority_input);
        callerNameInput = findViewById(R.id.caller_name_input);
        callerPhoneNumInput = findViewById(R.id.caller_phone_num_input);
        callerLocInput = findViewById(R.id.caller_loc_input);
        descriptionInput = findViewById(R.id.description_input);
        recCallPathInput = findViewById(R.id.rec_call_path_input);
        datetimeReceivedInput = findViewById(R.id.datetime_received_input);

        HashMap<String, String> updateMap = new HashMap<>();
        updateMap.put("type", typeInput.getSelectedItem().toString());
        updateMap.put("priority", priorityInput.getText().toString());
        updateMap.put("caller_name", callerNameInput.getText().toString());
        updateMap.put("caller_phone_num", callerPhoneNumInput.getText().toString());
        updateMap.put("caller_loc", callerLocInput.getText().toString());
        updateMap.put("description", descriptionInput.getText().toString());
        updateMap.put("rec_call_path", recCallPathInput.getText().toString());
        updateMap.put("datetime_received", datetimeReceivedInput.getText().toString());

        int id = intentData.getInt("id");
        dbHelper.updateDistressCallsInfo(id, updateMap);

        i = new Intent(this, DistressCallInfoActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.putExtra("id", id);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
