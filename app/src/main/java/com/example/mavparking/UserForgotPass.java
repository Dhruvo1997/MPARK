package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class UserForgotPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgot_pass);

        EditText Email_input = findViewById(R.id.Username_input);

        Button ShowQuestion= findViewById(R.id.ShowQuestion);

        AtomicBoolean validated=new AtomicBoolean(true);

        TextView Question = (TextView) findViewById(R.id.Question);
        Question.setVisibility(View.GONE);
        EditText Question_input = (EditText) findViewById(R.id.Question_input);
        Question_input.setVisibility(View.GONE);
        Button Submit=findViewById(R.id.Submit);
        Submit.setVisibility(View.GONE);
        Button GoToLogin=findViewById(R.id.BackToLogin);
        Context context=getApplicationContext();

        ShowQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String Email = Email_input.getText().toString();


                if(!Login.isEmailValid(Email))
                {
                    MainActivity.showToast(context, "Invalid Email Input!");
                    validated.set(false);
                }
                else
                {
                    validated.set(true);
                    if(MainActivity.Search_firebase(Email))
                    {
                        Question.setText(MainActivity.getQuestion());
                        Question.setVisibility(View.VISIBLE);
                        Question_input.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        MainActivity.showToast(context, "Email Not in system");
                    }
                }




            }
        });

        Submit.setOnClickListener(view ->
        {
            String Answer = Question_input.getText().toString();
            if(MainActivity.CheckAnswer(Answer))
            {
                MainActivity.showToast(context, "Password is: "+MainActivity.Database_password);
                Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(downloadIntent);
            }
            else
            {
                MainActivity.showToast(context, "Incorrect Answer");
            }
        });

        GoToLogin.setOnClickListener(view -> {
            Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
            startActivity(downloadIntent);
        });
    }
}