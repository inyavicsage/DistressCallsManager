package com.inyavic.inyavicsage.distresscallsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class AddDistressCallInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distress_call_info);

        Button addButton = findViewById(R.id.login_button);

        addButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Spinner typeInput = findViewById(R.id.type_input);
                        TextView priorityInput = findViewById(R.id.priority_input);
                        EditText callerNameInput = findViewById(R.id.caller_name_input);
                        TextView callerPhoneNumInput = findViewById(R.id.caller_phone_num_input);
                        EditText callerLocInput = findViewById(R.id.caller_loc_input);
                        EditText descriptionInput = findViewById(R.id.description_input);
                        TextView recCallPathInput = findViewById(R.id.rec_call_path_input);
                    }
                }
        );
    }
}
