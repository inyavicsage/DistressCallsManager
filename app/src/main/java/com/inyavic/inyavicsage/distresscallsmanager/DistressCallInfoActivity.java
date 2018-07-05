package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DistressCallInfoActivity extends AppCompatActivity {

    TextView id;
    TextView callerName;
    TextView type;
    TextView callerPhoneNum;
    TextView priority;
    TextView callerLoc;
    TextView description;
    TextView datetimeReceived;
    int DCI_ID;
    Bundle intentData;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distress_call_info);

        intentData = getIntent().getExtras();
        id = findViewById(R.id.id);
        callerName = findViewById(R.id.caller_name);
        type = findViewById(R.id.type);
        callerPhoneNum = findViewById(R.id.caller_phone_num);
        priority = findViewById(R.id.priority);
        callerLoc = findViewById(R.id.caller_loc);
        description = findViewById(R.id.description);
        datetimeReceived = findViewById(R.id.datetime_received);

        DCI_ID = intentData.getInt("id");
        ArrayList<DistressCallsInfo> DCI = dbHelper.getDistressCallsInfo(DCI_ID);

        id.setText(String.format("%s %s", getString(R.string.id_label), DCI.get(0).get_id()));
        callerName.setText(String.format("%s %s", getString(R.string.caller_name_label), DCI.get(0).getCallerName()));
        type.setText(String.format("%s %s", getString(R.string.type_label), DCI.get(0).getType()));
        callerPhoneNum.setText(String.format("%s %s", getString(R.string.caller_phone_num_label), DCI.get(0).getCallerPhoneNum()));
        priority.setText(String.format("%s %s", getString(R.string.priority_label), DCI.get(0).getPriority()));
        callerLoc.setText(String.format("%s %s", getString(R.string.caller_loc_label), DCI.get(0).getCallerLoc()));
        description.setText(String.format("%s %s", getString(R.string.description_label), DCI.get(0).getDescription()));
        datetimeReceived.setText(String.format("%s %s", getString(R.string.datetime_received_label), DCI.get(0).getDatetimeReceived()));
    }

    public void updateButtonClicked(View view) {
        i = new Intent(this, UpdateDistressCallInfoActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.putExtra("id", DCI_ID);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        i = new Intent(this, DistressCallInfoListActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void deleteButtonClicked(View view) {
        dbHelper.deleteDistressCallsInfo(DCI_ID);
        i = new Intent(this, DistressCallInfoListActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
