package com.example.mavparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //These values should all come from database!!!
    static String Database_email = "animal@gmail.com";
    static String Database_usertype="Professor";
    static String Database_Fname;
    static String Database_Lname;
    static String Database_password="1234";
    static String Database_PhoneNumber;
    static String Database_Question="Whats your dads name?";
    static String Database_Answer="nick";
    static int Database_ChargesDue=0;//should be loaded from database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button User=findViewById(R.id.User);
        Button Guest=findViewById(R.id.Guest);

        User.setOnClickListener(view -> {

            Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
            startActivity(downloadIntent);
        });

        Guest.setOnClickListener(v -> {
            Intent downloadIntent = new Intent(getApplicationContext(), GuestLogin.class);
            startActivity(downloadIntent);
        });


    }
    public static boolean Write_to_Firebase(String Email, String FName, String LName, String Usertype, String password, String PhoneNumber, String Question,  String Answer)
    {
        //Write to firebase the data of user
        Log.v("Method", "Writing to FireBase");
        Log.v("Method", Email);
        Log.v("Method", FName);
        Log.v("Method", LName);
        Log.v("Method",  Usertype);
        Log.v("Method", password);
        //Log.v("Method", PhoneNumber);
        Log.v("Method", Question);
        Log.v("Method", Answer);

        //returns 1 if write was successful
        //return 0 if it already exists


        return true;//temp return value
    }
    public static boolean Guest_Write_to_Firebase(String PhoneNumber, String FName, String LName, String Usertype)
    {
        //Write to firebase the data of user
        Log.v("Method", "Writing Guest to FireBase");
        Log.v("Method", PhoneNumber);
        Log.v("Method", FName);
        Log.v("Method", LName);
        Log.v("Method",  Usertype);


        //returns 1 if write was successful
        //return 0 if it already exists


        return true;//temp return value
    }
    public static boolean Search_firebase(String Email)
    {
        //find Email
        //Copy Data from data base into main so i can access later
        Log.v("Method", "Searching FireBase");
        Log.v("Method", Email);
        //returns 1 if found
        //returns 0 if not found
        return true;//temp return value
    }
    public static boolean Guest_Search_firebase(String PhoneNumber)
    {
        //find Phone number
        //Copy Data from data base into main so i can access later
        Log.v("Method", "Searching FireBase");
        Log.v("Method", PhoneNumber);
        //returns 1 if found
        //returns 0 if not found
        return true;//temp return value
    }

    public static void showToast(Context context, String text)
    {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 100, 300);
        toast.show();
    }
    public static boolean CheckPassword(String password_input)
    {
        return Database_password.matches("1234");
        //return Database_password.matches(password_input);
    }
    public static boolean CheckAnswer(String answer_input)
    {
        return Database_Answer.matches(answer_input);
    }
    public static String getQuestion()
    {
        return Database_Question;
    }


}