package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabase dbHelper;
    String[] HomeActivityAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        dbHelper = new MyDatabase(this);
        displayData();
    }

    @Override
    public void onClick(View v) {

    }

    private void displayData() {
        // Get all data from the database
        Cursor cursor = dbHelper.displayAllData();

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
            idTextView.setText(cursor.getString(cursor.getColumnIndex(MyDatabase.ID)));

            TextView messageTextView = new TextView(this);
            messageTextView.setText(cursor.getString(cursor.getColumnIndex(MyDatabase.MESSAGE)));

            TextView dateTextView = new TextView(this);
            dateTextView.setText(cursor.getString(cursor.getColumnIndex(MyDatabase.DATE)));

            // Set layout parameters for each TextView
            idTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            messageTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    2.0f));

            dateTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            // Add TextViews to the rowLayout
            rowLayout.addView(idTextView);
            rowLayout.addView(messageTextView);
            rowLayout.addView(dateTextView);

            // Add the rowLayout to the dataLayout
            dataLayout.addView(rowLayout);
        }

        // Close the cursor to avoid memory leaks
        cursor.close();
    }
}