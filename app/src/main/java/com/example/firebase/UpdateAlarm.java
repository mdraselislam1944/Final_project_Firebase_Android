package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateAlarm extends AppCompatActivity implements View.OnClickListener{


    MyHelperDB myHelperDB;
    private Button saveBtn,NewAlarmSet; // Correct variable name
    private TimePicker timePicker;
    private Calendar calendar;
    private DatePicker datePicker;
    private EditText messageId,id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alarm);
        myHelperDB=new MyHelperDB(this);
        myHelperDB.getWritableDatabase();

        id=findViewById(R.id.AlarmIdUpdate);
        messageId=findViewById(R.id.setMessage);
        calendar = Calendar.getInstance();
        timePicker = findViewById(R.id.TimeCount);
        datePicker = findViewById(R.id.datePicker);
//        NewAlarmSet=findViewById(R.id.NewAlarm);
        saveBtn = findViewById(R.id.SaveBtn); // Correct ID for the button
//        NewAlarmSet=findViewById(R.id.NewAlarm);
        saveBtn.setOnClickListener(this);
//        NewAlarmSet.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SaveBtn) {
            int ID = Integer.parseInt(id.getText().toString());
            int year = datePicker.getYear();
            int month = datePicker.getMonth() + 1; // Months are zero-based, so add 1
            int day = datePicker.getDayOfMonth();
            String message = messageId.getText().toString().trim();
            int hour;
            int minute;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                hour = timePicker.getHour();
                minute = timePicker.getMinute();
            } else {
                // For API level below 23
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
            }

            if (!message.isEmpty()) {
                long rowId = myHelperDB.updateData(ID, year, month, day, hour, minute, message);
                if (rowId > 0) {
                    // Data update successful
                    Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_LONG).show();

                    String selectedDateTime = "Updated date and time: " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
                    Toast.makeText(UpdateAlarm.this, selectedDateTime, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateAlarm.this, ShowActivity.class);
                    startActivity(intent);


                } else {
                    // Data update failed
                    Toast.makeText(getApplicationContext(), "Failed to update data. Row not found.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Please enter both message and date.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}