package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DistressCallManagersListActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_distress_call_managers_list);

        intentData = getIntent().getExtras();
        table = findViewById(R.id.table);
        ArrayList<DistressCallsManagers> DCMs = dbHelper.getDistressCallsManagers();

        for (int i = 0; i < DCMs.size(); i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setPadding(getPxVal(5), getPxVal(2), getPxVal(5), getPxVal(2));

            TextView username = new TextView(this);
            username.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT
            ));
            username.setText(DCMs.get(i).getUsername());
            username.setTextSize(20);

            ImageView updateIcon = new ImageView(this);
            updateIcon.setTag(DCMs.get(i).get_id());
            updateIcon.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, getPxVal(25)
            ));
            updateIcon.setContentDescription("update");
            updateIcon.setImageDrawable(
                    ContextCompat.getDrawable(this, android.R.drawable.ic_menu_edit)
            );
            updateIcon.setOnClickListener(this);

            ImageView deleteIcon = new ImageView(this);
            deleteIcon.setTag(DCMs.get(i).get_id());
            deleteIcon.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, getPxVal(25)
            ));
            deleteIcon.setContentDescription("delete");
            deleteIcon.setImageDrawable(
                    ContextCompat.getDrawable(this, android.R.drawable.ic_menu_delete)
            );
            deleteIcon.setOnClickListener(this);

            row.addView(username);
            row.addView(updateIcon);
            row.addView(deleteIcon);
            table.addView(row);
        }
    }

    public int getPxVal(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return ((int) (dp * scale + 0.5f));
    }

    public void addButtonClicked(View v) {
        i = new Intent(this, AddDistressCallManagerActivity.class);
        i.putExtra("username", intentData.getString("username"));
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        String username = intentData.getString("username");

        if (v.getContentDescription().toString().equals("update")) {
            i = new Intent(this, UpdateDistressCallManagerActivity.class);
            i.putExtra("username", username);
            i.putExtra("id", v.getTag().toString());
            startActivity(i);
        } else if (v.getContentDescription().toString().equals("delete")) {
            dbHelper.deleteDistressCallsManager((int) v.getTag());
            finish();
            startActivity(getIntent());
        }
    }
}
