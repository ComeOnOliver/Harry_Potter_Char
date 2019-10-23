package com.example.harry_potter_char;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //the authendication key for the First Harry Potter API
    private String theKey = "$2a$10$Ix4v5JXafFjU6wt2udyrx.QjP31jz3TSnys7DgE9PFRVNjY8SymZu";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void picClick(View v) {

        //This basically returns 9 characters' id to be searched in the apu
        String charId = "";
        switch (v.getId()) {

            case R.id.image1:
                // Harry Potter
                charId = "5a12292a0f5ae10021650d7e";
                break;

            case R.id.image2:
                // Hermione Granger
                charId = "5a109af13dc2080021cd877a";
                break;

            case R.id.image3:
                // Ronald Weasley
                charId = "5a1239c80f5ae10021650dad";
                break;

            case R.id.image4:
                // Albus Dumbledore
                charId = "5a1097653dc2080021cd8763";
                break;

            case R.id.image5:
                // Filius Flitwick
                charId = "5a1099cf3dc2080021cd8772";
                break;

            case R.id.image6:
                // Argus Filch
                charId = "5a1098653dc2080021cd876c";
                break;

            case R.id.image7:
                // Rubeus Hagrid
                charId = "5a109c3d3dc2080021cd8780";
                break;

            case R.id.image8:
                // Severus Snape
                charId = "5a1233bc0f5ae10021650d97";
                break;

            case R.id.image9:
                // Minerva McGonagall
                charId = "5a1223ed0f5ae10021650d70";
                break;

            default:
                charId = "";
                break;
        }
//To fetch data from the api, a class is made to handle the multiple-threads process and process data.

        fetchData process = new fetchData();
        process.execute(charId, theKey);
        Intent startNewActivity = new Intent(this, DisplayPerson.class);
        startActivity(startNewActivity);
    }


}
