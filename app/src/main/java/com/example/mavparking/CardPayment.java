package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        EditText FullName = findViewById(R.id.Name);
        EditText CardNumber = findViewById(R.id.Number);
        EditText ExpDate = findViewById(R.id.Expiration);
        EditText Security = findViewById(R.id.Code);
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
                String name = FullName.getText().toString();
                String number = CardNumber.getText().toString();
                String date = ExpDate.getText().toString();

                String security = Security.getText().toString();
                if(number.length() != 16)
                {
                    Toast toast = Toast.makeText(context, "Invalid Card Number!", duration);
                    toast.show();
                }
                else if(date.length() != 6)
                {
                    Toast toast = Toast.makeText(context, "Invalid Expiration Date!", duration);
                    toast.show();
                }
                else if(security.length() != 3)
                {
                    Toast toast = Toast.makeText(context, "Invalid Security Code!", duration);
                    toast.show();
                }
                else
                {
                    Intent downloadIntent = new Intent(getApplicationContext(), PaymentSuccess.class);
                    downloadIntent.putExtra("NAME",name);
                    startActivity(downloadIntent);


                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(context, "Cancelling Card Payment!", duration);
                toast.show();
                Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
                startActivity(downloadIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
                startActivity(downloadIntent);
            }
        });
    }
}