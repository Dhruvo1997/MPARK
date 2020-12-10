package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CashPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_payment);

        Button exit = findViewById(R.id.Exit);
        Button cancel = findViewById(R.id.Cancel);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Intent intent = getIntent();
        final String cash = intent.getStringExtra("CASH");

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
                startActivity(downloadIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
                startActivity(downloadIntent);
                Toast toast = Toast.makeText(context, "Canceling cash payment!", duration);
                toast.show();
            }
        });
    }
}