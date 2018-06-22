package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DistressCallInfoActivity extends AppCompatActivity {

    TableLayout table;
    Bundle intentData;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distress_call_info);

        intentData = getIntent().getExtras();
        table = findViewById(R.id.table);

        final int DCI_ID = intentData.getInt("id");
        ArrayList<DistressCallsInfo> DCI = dbHelper.getDistressCallsInfo(DCI_ID);

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowLayoutParams2 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        TableRow row1 = new TableRow(this);
        row1.setLayoutParams(tableLayoutParams);
        row1.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView id = new TextView(this);
        id.setLayoutParams(rowLayoutParams);
        id.setText(String.format("%s%s", getString(R.string.id_label), DCI.get(0).get_id()));
        id.setTextSize(18);

        TextView callerName = new TextView(this);
        callerName.setLayoutParams(rowLayoutParams);
        callerName.setText(String.format("%s%s", getString(R.string.caller_name_label), DCI.get(0).getCallerName()));
        callerName.setTextSize(18);

        row1.addView(id);
        row1.addView(callerName);

        TableRow row2 = new TableRow(this);
        row2.setLayoutParams(tableLayoutParams);
        row2.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView type = new TextView(this);
        type.setLayoutParams(rowLayoutParams);
        type.setText(String.format("%s%s", getString(R.string.type_label), DCI.get(0).getType()));
        type.setTextSize(18);

        TextView callerPhoneNum = new TextView(this);
        callerPhoneNum.setLayoutParams(rowLayoutParams);
        callerPhoneNum.setText(String.format("%s%s", getString(R.string.caller_phone_num_label), DCI.get(0).getCallerPhoneNum()));
        callerPhoneNum.setTextSize(18);

        row2.addView(type);
        row2.addView(callerPhoneNum);

        TableRow row3 = new TableRow(this);
        row3.setLayoutParams(tableLayoutParams);
        row3.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView priority = new TextView(this);
        priority.setLayoutParams(rowLayoutParams);
        priority.setText(String.format("%s%s", getString(R.string.priority_label), DCI.get(0).getPriority()));
        priority.setTextSize(18);

        TextView callerLoc = new TextView(this);
        callerLoc.setLayoutParams(rowLayoutParams);
        callerLoc.setText(String.format("%s%s", getString(R.string.caller_loc_label), DCI.get(0).getCallerLoc()));
        callerLoc.setTextSize(18);

        row3.addView(priority);
        row3.addView(callerLoc);

        TableRow row4 = new TableRow(this);
        row4.setLayoutParams(tableLayoutParams);
        row4.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView description = new TextView(this);
        TableRow.LayoutParams descriptionLayoutParams = rowLayoutParams;
        //descriptionLayoutParams.span = 2;
        description.setLayoutParams(descriptionLayoutParams);
        description.setText(String.format("%s%s", getString(R.string.description_label), DCI.get(0).getDescription()));
        description.setTextSize(18);

        row4.addView(description);

        TableRow row5 = new TableRow(this);
        row5.setLayoutParams(tableLayoutParams);
        row5.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView datetimeReceived = new TextView(this);
        datetimeReceived.setLayoutParams(rowLayoutParams);
        datetimeReceived.setText(String.format("%s%s", getString(R.string.datetime_received_label), DCI.get(0).getDatetimeReceived()));
        datetimeReceived.setTextSize(18);

        row5.addView(datetimeReceived);

        TableRow row6 = new TableRow(this);
        row6.setLayoutParams(tableLayoutParams);
        row6.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        TextView recCall = new TextView(this);
        recCall.setLayoutParams(rowLayoutParams);
        recCall.setText(R.string.rec_call_label);
        recCall.setTextSize(18);

        ImageView playIcon = new ImageView(this);
        playIcon.setLayoutParams(rowLayoutParams2);
        playIcon.setContentDescription("play");
        playIcon.setImageDrawable(
                ContextCompat.getDrawable(this, android.R.drawable.ic_media_play)
        );

        row6.addView(recCall);
        row6.addView(playIcon);

        TableRow row7 = new TableRow(this);
        row7.setLayoutParams(tableLayoutParams);
        row7.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

        Button updateButton = new Button(this);
        updateButton.setLayoutParams(rowLayoutParams2);
        updateButton.setText(R.string.update_button_text);
        updateButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(DistressCallInfoActivity.this, UpdateDistressCallInfoActivity.class);
                i.putExtra("username", intentData.getString("username"));
                i.putExtra("id", DCI_ID);
                startActivity(i);
                finish();
            }
        });

        Button deleteButton = new Button(this);
        deleteButton.setLayoutParams(rowLayoutParams2);
        deleteButton.setText(R.string.delete_button_text);
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteDistressCallsInfo(DCI_ID);
                i = new Intent(DistressCallInfoActivity.this, DistressCallInfoListActivity.class);
                i.putExtra("username", intentData.getString("username"));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        row7.addView(updateButton);
        row7.addView(deleteButton);

        table.addView(row1);
        table.addView(row2);
        table.addView(row3);
        table.addView(row4);
        table.addView(row5);
        table.addView(row6);
        table.addView(row7);
    }

    public int getPxVal(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return ((int) (dp * scale + 0.5f));
    }
}
