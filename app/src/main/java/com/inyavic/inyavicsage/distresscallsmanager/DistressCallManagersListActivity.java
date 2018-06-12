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

public class DistressCallManagersListActivity extends AppCompatActivity implements View.OnClickListener {

    TableLayout table;
    Button add;
    Intent i;
    MyDBHelper dbHelper = new MyDBHelper(this, null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distress_call_managers_list);

        table = findViewById(R.id.table);
        ArrayList<DistressCallsManagers> DCMs = dbHelper.getDistressCallsManagers();

        for (int i = 0; i < DCMs.size(); i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setPadding(getPxVal(2), getPxVal(2), getPxVal(2), getPxVal(2));

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
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if (v.getContentDescription().toString().equals("update")) {
            i = new Intent(this, UpdateDistressCallManagerActivity.class);
            i.putExtra("id", v.getTag().toString());
            startActivity(i);
        } else if (v.getContentDescription().toString().equals("delete")) {
            dbHelper.deleteDistressCallsManager((int) v.getTag());
            finish();
            startActivity(getIntent());
        }
    }
}
