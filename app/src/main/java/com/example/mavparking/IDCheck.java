package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_d_check);

        Button check = findViewById(R.id.Check);
        Button cancel = findViewById(R.id.Cancel);
        Button exit = findViewById(R.id.Exit);

        EditText id = findViewById(R.id.ID);

        TextView type = findViewById(R.id.Type);
        type.setVisibility(View.GONE);
        TextView number = findViewById(R.id.Number);
        number.setVisibility(View.GONE);
        TextView date = findViewById(R.id.Date);
        date.setVisibility(View.GONE);
        TextView message = findViewById(R.id.Message);
        message.setVisibility(View.GONE);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        final int[] found = {0};  //to keep track if the user was found or not
        final int[] user = {0};

        String [][] users = {{"bistmadhav@gmail.com","Student","Madhav","Bist","1234","0123456789","In what city was your first full time job?","Irving","500","Reserved-Lots","12-16-2020"},
                {"cindy.sante@mavs.uta.edu","Student","Cindy","Sante","pass1324","2144657895","What middle school did you attend?","Harwood","250","Motorcycle","8-8-2021"},
                {"timmy.turner@mavs.uta.edu","Professor","Timmy","Turner","pass1423","9721237895","Favorite TV show?","Rick and Morty","1080","Faculty Lots","5-13-2020"}};

        check.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String UserID = id.getText().toString();

                for(int i = 0; i < 3; i++)
                {
                    if (UserID.matches(users[i][0]))
                    {
                        Toast toast = Toast.makeText(context, "Valid User ID!", duration);
                        toast.show();

                        found[0] = 1;
                        user[0] = i;

                        if(found[0] == 1)
                        {
                            String permit = users[user[0]][9].toString();
                            type.setText(permit);
                            type.setVisibility(View.VISIBLE);
                            number.setText(users[user[0]][5]);
                            number.setVisibility(View.VISIBLE);
                            date.setText(users[user[0]][10]);
                            date.setVisibility(View.VISIBLE);

                            String [] values = users[user[0]][10].split("-");
                            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                            String currentDate = sdf.format(new Date());
                            String [] splitDate = currentDate.split("-");
                            int year1 = Integer.parseInt(values[2]);
                            int month1 = Integer.parseInt(values[0]);
                            int day1 = Integer.parseInt(values[1]);
                            int year2 = Integer.parseInt(splitDate[2]);
                            int month2 = Integer.parseInt(splitDate[0]);
                            int day2 = Integer.parseInt(splitDate[1]);

                            if((year1 > year2))
                            {
                                message.setText("Permit is good!");
                                message.setVisibility(View.VISIBLE);
                            }
                            else if((year1 == year2) && (month1 > month2))
                            {
                                message.setText("Permit is good!");
                                message.setVisibility(View.VISIBLE);
                            }
                            else if((year1 == year2) && (month1 == month2) && (day1 > day2))
                            {
                                message.setText("Permit is good!");
                                message.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                message.setText("Permit is EXPIRED! Permit Renewal required and user will be notified!");
                                message.setVisibility(View.VISIBLE);
                            }
                        }

                        break;
                    }
                }
                if(found[0] == 0)
                {
                    Toast toast = Toast.makeText(context, "INVALID User ID!", duration);
                    toast.show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(context, "Cancelling Query", duration);
                toast.show();
                Intent downloadIntent = new Intent(getApplicationContext(), Communication.class);
                startActivity(downloadIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), Communication.class);
                startActivity(downloadIntent);
            }
        });
    }
}