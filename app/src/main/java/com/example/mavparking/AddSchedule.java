package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        EditText id = findViewById(R.id.ID);
        EditText days = findViewById(R.id.Days);
        EditText times = findViewById(R.id.Times);
        EditText locations = findViewById(R.id.Locations);

        Button submit = findViewById(R.id.Submit);
        Button cancel = findViewById(R.id.Cancel);
        Button exit = findViewById(R.id.Exit);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String time = times.getText().toString();
                if(time.length() != 9)
                {
                    Toast toast = Toast.makeText(context, "Invalid Time Input!", duration);
                    toast.show();
                }
                else
                {
                    Intent downloadIntent = new Intent(getApplicationContext(), SubmitSchedule.class);
                    startActivity(downloadIntent);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(context, "Cancelling Schedule Addition!", duration);
                toast.show();
                Intent downloadIntent = new Intent(getApplicationContext(), ShuttleInfo.class);
                startActivity(downloadIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), ShuttleInfo.class);
                startActivity(downloadIntent);
            }
        });
    }
}