package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        AtomicBoolean Valid_inputs= new AtomicBoolean(true);

        Spinner spinner = (Spinner) findViewById(R.id.UserType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Users, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText FirstName_input = findViewById(R.id.FirstName_input);
        EditText LastName_input = findViewById(R.id.LastName_input);
        EditText Email_input = findViewById(R.id.Email_input);
        EditText Password_input = findViewById(R.id.Password_input);
        EditText Number_input = findViewById(R.id.Number_input);

        Spinner Qspinner = (Spinner) findViewById(R.id.QuestionsSpinner);
        ArrayAdapter<CharSequence> Qadapter = ArrayAdapter.createFromResource(this,
                R.array.Questions, android.R.layout.simple_spinner_item);
        Qadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Qspinner.setAdapter(Qadapter);

        EditText Answer_input = findViewById(R.id.Answer_input);
        Button Register=findViewById(R.id.Register);
        Button GoToLogin=findViewById(R.id.GoToLogin);




        Register.setOnClickListener(view -> {

            //save data in firebase
            String type = spinner.getSelectedItem().toString();
            String FirstName = FirstName_input.getText().toString();
            String LastName = LastName_input.getText().toString();
            String Email = Email_input.getText().toString();
            String Password = Password_input.getText().toString();
            String Number =  Number_input.getText().toString();
            String Question = Qspinner.getSelectedItem().toString();
            String Answer = Answer_input.getText().toString();

            Context context = getApplicationContext();

            if(!Login.isEmailValid(Email))
            {
                MainActivity.showToast(context, "Invalid Email Input!");
                Valid_inputs.set(false);
            }
            else
            {
                Valid_inputs.set(true);
            }

            if(Valid_inputs.get())
            {

                boolean wroteToFirebase = MainActivity.Write_to_Firebase(Email, FirstName, LastName, type, Password, Number, Question, Answer);
                if(wroteToFirebase)
                {
                    MainActivity.showToast(context, "Saved In Database!!!");
                    Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(downloadIntent);
                }
                else
                {
                    MainActivity.showToast(context, "User Already In Database!!!");
                    Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(downloadIntent);
                }
            }

        });


        GoToLogin.setOnClickListener(view -> {
            Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
            startActivity(downloadIntent);
        });
    }
}