package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddDistressCallInfoActivity extends AppCompatActivity {

    Spinner typeInput;
    TextView priorityInput;
    EditText callerNameInput;
    TextView callerPhoneNumInput;
    EditText callerLocInput;
    EditText descriptionInput;
    TextView datetimeReceivedInput;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);
    Bundle intentData;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distress_call_info);

        intentData = getIntent().getExtras();
        typeInput = findViewById(R.id.type_input);
        priorityInput = findViewById(R.id.priority_input);
        callerPhoneNumInput = findViewById(R.id.caller_phone_num_input);
        datetimeReceivedInput = findViewById(R.id.datetime_received_input);

        priorityInput.setText("1");
        callerPhoneNumInput.setText(intentData.getString("caller_phone_num"));
        datetimeReceivedInput.setText(intentData.getString("datetime_received"));
    }

    public void addButtonClicked(View view) {
        typeInput = findViewById(R.id.type_input);
        priorityInput = findViewById(R.id.priority_input);
        callerNameInput = findViewById(R.id.caller_name_input);
        callerPhoneNumInput = findViewById(R.id.caller_phone_num_input);
        callerLocInput = findViewById(R.id.caller_loc_input);
        descriptionInput = findViewById(R.id.description_input);
        datetimeReceivedInput = findViewById(R.id.datetime_received_input);

        DistressCallsInfo DCI = new DistressCallsInfo(typeInput.getSelectedItem().toString(),
                priorityInput.getText().toString(), callerNameInput.getText().toString(),
                callerPhoneNumInput.getText().toString(), callerLocInput.getText().toString(),
                descriptionInput.getText().toString(), "recCallPathInput.getText().toString()",
                datetimeReceivedInput.getText().toString());
        dbHelper.addDistressCallsInfo(DCI);

        i = new Intent(this, DistressCallInfoListActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
