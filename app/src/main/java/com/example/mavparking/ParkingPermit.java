package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class ParkingPermit extends AppCompatActivity {
    double[][][] PriceArr ={
            {{1850,597,276},{1304,416,200},{648,195,168},{460,211,150},{300,131,125},{210,98,80},{160,74,62},{115,45,45},{105,48,41}},
            {{1850,597,276},{1304,416,200},{648,195,168},{825,416,260},{273,178,115},{200,122,78},{115,70,45}},
            {{414,190,161},{207,95,80},{115,45,45}},
            {{405,240,152},{9,40,80},{4,4,13}}
    };
    int user,permit,dur;
    double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_permit);
        Context context = getApplicationContext();

        TextView Totaltxt = (TextView) findViewById(R.id.Totaltxt);
        Totaltxt.setVisibility(View.GONE);

        Button MainMenu =findViewById(R.id.BackToHomeScreen);
        Spinner spinner = (Spinner) findViewById(R.id.UserType);
        Spinner Durationspinner = (Spinner) findViewById(R.id.Duration);


        Button Payment=findViewById(R.id.Payment);
        Payment.setVisibility(View.GONE);
        Button Calculate = findViewById(R.id.Calculate);

        ArrayAdapter<CharSequence> Qadapter;
        ArrayAdapter<CharSequence> adapter;
        if(MainActivity.Database_usertype.matches("Guest"))
        {
            user=3;
            Qadapter = ArrayAdapter.createFromResource(this,
                    R.array.Guest, android.R.layout.simple_spinner_item);
            Qadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(Qadapter);


            adapter = ArrayAdapter.createFromResource(this,
                    R.array.GuestDuration, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Durationspinner.setAdapter(adapter);
        }
        else if(MainActivity.Database_usertype.matches("Resident Student"))
        {
            user=2;
            Qadapter = ArrayAdapter.createFromResource(this,
                    R.array.ResidentStudent, android.R.layout.simple_spinner_item);
            Qadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(Qadapter);

            adapter = ArrayAdapter.createFromResource(this,
                    R.array.SemesterDuration, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Durationspinner.setAdapter(adapter);
        }
        else if(MainActivity.Database_usertype.matches("Professor") || MainActivity.Database_usertype.matches("Security"))
        {
            user=1;
            Qadapter = ArrayAdapter.createFromResource(this,
                    R.array.StaffPermits, android.R.layout.simple_spinner_item);
            Qadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(Qadapter);

            adapter = ArrayAdapter.createFromResource(this,
                    R.array.SemesterDuration, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Durationspinner.setAdapter(adapter);
        }
        else
        {
            user=0;
            Qadapter = ArrayAdapter.createFromResource(this,
                    R.array.Off_CampusStudents, android.R.layout.simple_spinner_item);
            Qadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(Qadapter);


            adapter = ArrayAdapter.createFromResource(this,
                    R.array.SemesterDuration, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Durationspinner.setAdapter(adapter);
        }


        Calculate.setOnClickListener(view ->
        {
            String s_permit = spinner.getSelectedItem().toString();
            permit=Qadapter.getPosition(s_permit);

            String s_dur = Durationspinner.getSelectedItem().toString();
            dur=adapter.getPosition(s_dur);
            total = PriceArr[user][permit][dur] + (PriceArr[user][permit][dur] *0.08);
            Totaltxt.setText("Total Amount Due: "+ Integer.toString((int) total));
            Totaltxt.setVisibility(View.VISIBLE);
            Payment.setVisibility(View.VISIBLE);
        });

        Payment.setOnClickListener(view ->
        {
            MainActivity.Database_ChargesDue+=total;
            Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
            downloadIntent.putExtra("TOTAL", MainActivity.Database_ChargesDue);
            startActivity(downloadIntent);
        });
        MainMenu.setOnClickListener(view ->
        {
            Intent downloadIntent = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(downloadIntent);
        });
    }


}