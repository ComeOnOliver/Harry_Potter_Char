package com.example.harry_potter_char;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DisplayPerson extends AppCompatActivity {

    public static TextView hpName;
    public static TextView hpRole;
    public static TextView hpHouse;
    public static TextView hpSchool;
    public static TextView hpSpecies;
    public static TextView hpBlood;
    public static EditText randJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // pass the information from the sub-thread to the main thread
        //To set up the textview

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person);

        String stringDisplayName = "";
        this.setTitle(stringDisplayName);

        hpName = findViewById(R.id.name);
        hpRole = findViewById(R.id.role);
        hpHouse = findViewById(R.id.house);
        hpSchool= findViewById(R.id.school);
        hpSpecies = findViewById(R.id.species);
        hpBlood = findViewById(R.id.blood);
        randJoke= findViewById(R.id.editText);


    }


}
