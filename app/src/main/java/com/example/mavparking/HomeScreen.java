package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener
{
    public CardView buy;
    public CardView add;
    public CardView  parking;
    public CardView shuttl;
    public CardView warning;
    public CardView communicate;
    public CardView contact;
    public CardView general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        buy =  (CardView)findViewById(R.id.Buy_A_Permit);
        add =  ( CardView)findViewById(R.id.Add_Remove_Car);
        parking = (CardView)findViewById(R.id.Parking_Info);
        shuttl =  (CardView)findViewById(R.id.Shuttl_info);
        warning = (CardView)findViewById(R.id.Warning_Viloation);
        communicate = (CardView)findViewById(R.id.Communications);
        contact = (CardView)findViewById(R.id.Conatact_Info);
        general = (CardView)findViewById(R.id.Make_Payment);
        buy.setOnClickListener(this);
        add.setOnClickListener(this);
        parking.setOnClickListener(this);
        shuttl.setOnClickListener(this);
        warning.setOnClickListener(this);
        communicate.setOnClickListener(this);
        contact.setOnClickListener(this);
        general.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent k;

        switch (v.getId())
        {
            case R.id.Buy_A_Permit:
                k= new Intent(this, ParkingPermit.class);


                startActivity(k);

                break;



            case R.id.Add_Remove_Car:
                k= new Intent(this, AddRemoveCar.class);

                startActivity(k);
                break;


            case R.id.Parking_Info:
                k= new Intent(this, ParkingInfo.class);

                startActivity(k);


                break;




            case R.id.Shuttl_info:
                k= new Intent(this, ShuttleInfo.class);

                startActivity(k);



                break;


            case R.id.Warning_Viloation:
                k= new Intent(this, WarningViolation.class);

                startActivity(k);


                break;



            case R.id.Communications:
                k= new Intent(this, Communication.class);

                startActivity(k);


                break;





            case R.id.Conatact_Info:
                k= new Intent(this, Contact.class);

                startActivity(k);

                break;


            case R.id.Make_Payment:
                k= new Intent(this, MakePayment.class);
                startActivity(k);
                break;

        }

    }
}