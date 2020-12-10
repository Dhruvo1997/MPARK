package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        Button exit = findViewById(R.id.Exit);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("NAME");

        TextView thanks = findViewById(R.id.Thanks);
        thanks.setVisibility(View.GONE);

        thanks.setText("Thank you for your payment " + name +"! It has been submitted to the Office of Transportation.");
        thanks.setVisibility(View.VISIBLE);

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String due = "0";
                Intent downloadIntent = new Intent(getApplicationContext(), MakePayment.class);
                downloadIntent.putExtra("SUCCESS", due);
                startActivity(downloadIntent);
            }
        });
    }
}