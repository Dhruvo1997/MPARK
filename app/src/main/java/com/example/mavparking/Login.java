package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    int Counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText Username_input = findViewById(R.id.Username_input);
        EditText Password_input = findViewById(R.id.Password_input);

        Button Login = findViewById(R.id.Login);
        TextView Register = findViewById(R.id.Registertxt);
        TextView Forgotpass = findViewById(R.id.Forgotpasstxt);

        AtomicBoolean validate = new AtomicBoolean(false);
        Context context = getApplicationContext();


        Login.setOnClickListener(view -> {

            String Email = Username_input.getText().toString();
            String Password = Password_input.getText().toString();

            if (!isEmailValid(Email)) {
                MainActivity.showToast(context, "Invalid Email Input!");
                validate.set(false);
            } else {
                validate.set(true);
            }
            if (validate.get()) {
                //Check if email exists
                if (MainActivity.Search_firebase(Email)) {
                    //check if password is a match 5 tries then send to forgot password

                    if (MainActivity.CheckPassword(Password)) {
                        //Send User to home screen they got the right password and email!
                        Intent downloadIntent = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(downloadIntent);
                    } else {
                        MainActivity.showToast(context, "Password is incorrect");
                        Counter++;
                    }
                    if (Counter > 4) {
                        Intent downloadIntent = new Intent(getApplicationContext(), UserForgotPass.class);
                        startActivity(downloadIntent);
                    }
                } else {
                    MainActivity.showToast(context, "You are not Registered");
                }

            }
        });


        Forgotpass.setOnClickListener(view ->
        {
            Intent downloadIntent = new Intent(getApplicationContext(), UserForgotPass.class);
            startActivity(downloadIntent);
        });

        //done!
        Register.setOnClickListener(view ->
        {
            Intent downloadIntent = new Intent(getApplicationContext(), RegisterUser.class);
            startActivity(downloadIntent);
        });


    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher match;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        match = pattern.matcher(email);
        return match.matches();
    }

}