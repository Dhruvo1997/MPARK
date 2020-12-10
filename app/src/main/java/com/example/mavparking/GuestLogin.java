package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class GuestLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        AtomicBoolean validate = new AtomicBoolean(false);
        EditText PhoneNumberIN = findViewById(R.id.Number_input);

        Button LoginAsGuest = findViewById(R.id.LoginAsGuest);
        TextView Register = findViewById(R.id.Registertxt);
        Context context = getApplicationContext();

        LoginAsGuest.setOnClickListener(view -> {

            String PhoneNumber =  PhoneNumberIN.getText().toString();

            if(!(PhoneNumber.length()==7 ||PhoneNumber.length()==8))
            {
                MainActivity.showToast(context, "Invalid Phone Number Input.");
                validate.set(false);
            }
            else
            {
                validate.set(true);
            }
            if(validate.get())
            {
                //Check if email exists
                if(MainActivity.Guest_Search_firebase(PhoneNumber))
                {
                    Intent downloadIntent = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(downloadIntent);
                }
                else
                {
                    //not in database
                    MainActivity.showToast(context, "You are not Registered.");
                }

            }
        });

        Register.setOnClickListener(view ->
        {
            Intent downloadIntent = new Intent(getApplicationContext(), RegisterGuest.class);
            startActivity(downloadIntent);
        });
    }
}