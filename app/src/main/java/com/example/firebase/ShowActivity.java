package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    private MyHelperDB dbHelper;
    String[] HomeActivityAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        dbHelper = new MyHelperDB(this);
        displayData();
    }

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("Range")
    private void displayData() {
        // Get all data from the database
        Cursor cursor = dbHelper.displayAllDataTime();

        // Find the LinearLayout where you want to display the data
        LinearLayout dataLayout = findViewById(R.id.dataLayout);

        // Loop through the cursor to display each row of data
        while (cursor.moveToNext()) {
            // Create a new LinearLayout for each row
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Create TextViews for each column
            TextView idTextView = new TextView(this);
            idTextView.setText(cursor.getString(cursor.getColumnIndex(MyHelperDB.ID)));

            TextView year = new TextView(this);
            year.setText(cursor.getString(cursor.getColumnIndex(MyHelperDB.YEAR)));

            TextView month = new TextView(this);
            month.setText(cursor.getString(cursor.getColumnIndex(MyHelperDB.MONTH)));

            TextView day = new TextView(this);
            day.setText(cursor.getString(cursor.getColumnIndex(MyHelperDB.DATE)));

            TextView hour = new TextView(this);
            hour.setText(formatTime(cursor.getInt(cursor.getColumnIndex(MyHelperDB.HOUR))));

            TextView minutes = new TextView(this);
            minutes.setText(formatTime(cursor.getInt(cursor.getColumnIndex(MyHelperDB.MINUTES))));

            TextView messageTextView = new TextView(this);
            messageTextView.setText(cursor.getString(cursor.getColumnIndex(MyHelperDB.MESSAGE)));

            // Set layout parameters for each TextView
            idTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            year.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            month.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            day.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));
            hour.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            minutes.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            messageTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            // Add TextViews to the rowLayout
            rowLayout.addView(idTextView);
            rowLayout.addView(year);
            rowLayout.addView(month);
            rowLayout.addView(day);
            rowLayout.addView(hour);
            rowLayout.addView(minutes);
            rowLayout.addView(messageTextView);

            // Add the rowLayout to the dataLayout
            dataLayout.addView(rowLayout);
        }

        // Close the cursor to avoid memory leaks
        cursor.close();
    }

    // Helper method to format time to HH:mm
    private String formatTime(int timeValue) {
        return String.format("%02d", timeValue);
    }


}