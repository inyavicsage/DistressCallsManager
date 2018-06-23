package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DistressCallInfoListActivity extends AppCompatActivity implements View.OnClickListener {

    TableLayout table;
    Button add;
    Bundle intentData;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dcm_list:
                i = new Intent(this, DistressCallManagersListActivity.class);
                break;
            case R.id.dci_list:
                i = new Intent(this, DistressCallInfoListActivity.class);
                break;
            case R.id.logout:
                i = new Intent(this, LoginActivity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        i.putExtra("username", intentData.getString("username"));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distress_call_info_list);

        intentData = getIntent().getExtras();
        table = findViewById(R.id.table);
        ArrayList<DistressCallsInfo> DCIs = dbHelper.getDistressCallsInfo(0);

        TableRow.LayoutParams rowLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < DCIs.size(); i++) {
            TableRow row = new TableRow(this);
            row.setId(DCIs.get(i).get_id());
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));
            row.setOnClickListener(this);

            TextView callerName = new TextView(this);
            callerName.setLayoutParams(rowLayoutParams);
            callerName.setText(DCIs.get(i).getCallerName());
            callerName.setTextSize(18);

            TextView priority = new TextView(this);
            TableRow.LayoutParams priorityLayoutParams = rowLayoutParams;
            //priorityLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            priority.setLayoutParams(priorityLayoutParams);
            priority.setText(DCIs.get(i).getPriority());
            priority.setTextSize(18);

            TextView dateTime = new TextView(this);
            dateTime.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT
            ));
            dateTime.setText(DCIs.get(i).getDatetimeReceived());
            dateTime.setTextSize(18);

            row.addView(callerName);
            row.addView(priority);
            row.addView(dateTime);
            table.addView(row);
        }
    }

    public int getPxVal(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return ((int) (dp * scale + 0.5f));
    }

    public void addButtonClicked(View v) {
        i = new Intent(this, AddDistressCallInfoActivity.class);
        i.putExtra("username", intentData.getString("username"));
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        i = new Intent(this, DistressCallInfoActivity.class);
        i.putExtra("username", intentData.getString("username"));
        i.putExtra("id", v.getId());
        startActivity(i);
    }
}
