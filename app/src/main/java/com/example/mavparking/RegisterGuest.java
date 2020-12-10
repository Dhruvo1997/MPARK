package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterGuest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guest);

        AtomicBoolean Valid_inputs= new AtomicBoolean(true);

        EditText FirstName_input = findViewById(R.id.FirstName_input);
        EditText LastName_input = findViewById(R.id.LastName_input);
        EditText Number_input = findViewById(R.id.Number_input);

        Button Register=findViewById(R.id.Register);
        Button GoToLogin=findViewById(R.id.GoToLogin);

        Register.setOnClickListener(view -> {

            //save data in firebase
            String FirstName = FirstName_input.getText().toString();
            String LastName = LastName_input.getText().toString();
            String Number =  Number_input.getText().toString();

            Context context = getApplicationContext();

            if(!(Number.length()==7))
            {
                MainActivity.showToast(context, "Invalid Phone Number Input!");
                Valid_inputs.set(false);
            }
            else
            {
                Valid_inputs.set(true);
            }
            if(Valid_inputs.get())
            {

                boolean wroteToFirebase = MainActivity.Guest_Write_to_Firebase(Number, FirstName, LastName, "Guest");
                if(wroteToFirebase)
                {
                    MainActivity.showToast(context, "Saved In Database!!!");
                    Intent downloadIntent = new Intent(getApplicationContext(), GuestLogin.class);
                    startActivity(downloadIntent);
                }
                else
                {
                    MainActivity.showToast(context, "User Already In Database!!!");
                    Intent downloadIntent = new Intent(getApplicationContext(), GuestLogin.class);
                    startActivity(downloadIntent);
                }
            }

        });

        GoToLogin.setOnClickListener(view -> {
            Intent downloadIntent = new Intent(getApplicationContext(), GuestLogin.class);
            startActivity(downloadIntent);
        });
    }
}