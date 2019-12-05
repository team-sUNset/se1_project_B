package com.example.la_cague;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



    }


    public void open_office(View view) {
        Intent intent_Inicio = new Intent(this, Office.class);
        startActivity(intent_Inicio);

    }


}
