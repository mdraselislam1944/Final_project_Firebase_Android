package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{


    MyHelperDB myHelperDB;
    private Button saveBtn,NewAlarmSet; // Correct variable name
    private TimePicker timePicker;
    private Calendar calendar;
    private DatePicker datePicker;
    private EditText messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myHelperDB=new MyHelperDB(this);
        myHelperDB.getWritableDatabase();

        messageId=findViewById(R.id.setMessage);
        calendar = Calendar.getInstance();
        timePicker = findViewById(R.id.TimeCount);
        datePicker = findViewById(R.id.datePicker);
//        NewAlarmSet=findViewById(R.id.NewAlarm);
        saveBtn = findViewById(R.id.SaveBtn); // Correct ID for the button
        NewAlarmSet=findViewById(R.id.NewAlarm);
        saveBtn.setOnClickListener(this);
        NewAlarmSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.NewAlarm){
            Intent intent=new Intent(MainActivity2.this, NewAlarm.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.SaveBtn) {
//            int hour = timePicker.getHour(); // Updated method for API level 23 and above
//            int minute = timePicker.getMinute();
//            String selectedTime = "Selected time: " + hour + ":" + minute;
//            Toast.makeText(MainActivity.this, selectedTime, Toast.LENGTH_SHORT).show();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH); // Note: Months are zero-based
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//            int minute = calendar.get(Calendar.MINUTE);
//            int second = calendar.get(Calendar.SECOND);
//            String time="Current date and time: " + year + "-" + (month + 1) + "-" + day + " " + hour + ":" + minute + ":" + second;
//            Toast.makeText(MainActivity.this, time, Toast.LENGTH_LONG).show();



            int year = datePicker.getYear();
            int month = datePicker.getMonth() + 1; // Months are zero-based, so add 1
            int day = datePicker.getDayOfMonth();
            String message=messageId.getText().toString().trim();
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
                long rowId=myHelperDB.InsertData(year,month,day,hour,minute,message);
                if(rowId==-1){
                    Toast.makeText(getApplicationContext(),"data save is not successfully",Toast.LENGTH_LONG).show();

                    String selectedDateTime = "Selected date and time: " + year + "-" + month + "-" + day + " " + hour + ":" + minute;
                    Toast.makeText(MainActivity2.this, selectedDateTime, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext()," row "+rowId+" data save is successfully",Toast.LENGTH_LONG).show();
                    messageId.setText("");
                }
            } else {
                Toast.makeText(this, "Please enter both message and date.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scheduleAlarmsForExistingData() {
        List<AlarmData> alarmDataList = myHelperDB.getAllAlarms();
        for (AlarmData alarmData : alarmDataList) {
            scheduleAlarm(alarmData.getYear(), alarmData.getMonth(), alarmData.getDay(),
                    alarmData.getHour(), alarmData.getMinute());
        }
    }

    private void scheduleAlarm(int year, int month, int day, int hour, int minute) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        intent.putExtra("message", "Your alarm message here");
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

//        Intent intent = new Intent(this, AlarmReceiver.class);

//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        long alarmTime = calendar.getTimeInMillis();

        // Set the alarm to trigger at the specified time
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
    }
}
