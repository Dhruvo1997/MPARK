package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShuttleInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_info);

        Button NewSchedule = findViewById(R.id.AddSchedule);
        Button EditSchedule = findViewById(R.id.EditSchedule);
        Button ViewSchedule = findViewById(R.id.ViewSchedule);
        Button exit = findViewById(R.id.Exit);

        NewSchedule.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), AddSchedule.class);
                startActivity(downloadIntent);
            }
        });

        EditSchedule.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), EditSchedule.class);
                startActivity(downloadIntent);
            }
        });

        ViewSchedule.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), ViewSchedule.class);
                startActivity(downloadIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(downloadIntent);
            }
        });
    }
}