package com.example.la_cague;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);



    }


    public void open_user(View view) {
        Intent intent_Inicio = new Intent(this, User.class);
        startActivity(intent_Inicio);

    }
    public void open_create(View view) {
        Intent intent_Inicio = new Intent(this, Create.class);
        startActivity(intent_Inicio);

    }
    public void open_delete(View view) {
        Intent intent_Inicio = new Intent(this, Delete.class);
        startActivity(intent_Inicio);

    }


}
