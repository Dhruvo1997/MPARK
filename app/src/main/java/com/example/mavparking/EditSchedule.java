package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        EditText id = findViewById(R.id.ID);
        EditText days = findViewById(R.id.Days);
        EditText times = findViewById(R.id.Times);
        EditText locations = findViewById(R.id.Locations);

        Button checkID = findViewById(R.id.CheckID);
        Button submit = findViewById(R.id.Submit);
        Button cancel = findViewById(R.id.Cancel);
        Button exit = findViewById(R.id.Exit);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        String [][] shuttles = {{"U001","M,T,W,TH,F","0600-1500","MAC,Walmart,SIER,Kroger,Bus Station"},
                {"U002","M,W,F","0900-1800","Station,MAC,University Center,SIER,Jack in the Box,Taco Bell"},
                {"U003","M,T,W,TH,F,SA,SU","1200-2300","MAC,Kroger,Walmart,PotBellies,Downtown FTW,Apple"}};

        final int[] validID = {0};

        checkID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String ShutID = id.getText().toString();

                for(int i = 0; i < 3; i++)
                {
                    if (ShutID.matches(shuttles[i][0])) {
                        Toast toast = Toast.makeText(context, "Valid Shuttle ID!", duration);
                        toast.show();
                        validID[0] = 1;
                        break;
                    }
                }
                if(validID[0] == 0)
                {
                    Toast toast = Toast.makeText(context, "INVALID Shuttle ID!", duration);
                    toast.show();
                }

            }
        });

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
                else if(validID[0] == 1)
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
                Toast toast = Toast.makeText(context, "Cancelling Schedule Edit!", duration);
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