package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.reserve.reserveFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);






    }


    public void open_user(View view) {
        Intent intent_Inicio = new Intent(this, MainMenuUser.class);
        startActivity(intent_Inicio);

    }

    /*
    public void open_create(View view) {
        Intent intent_Inicio = new Intent(this, Create.class);
        startActivity(intent_Inicio);

    }
    public void open_delete(View view) {
        Intent intent_Inicio = new Intent(this, Delete.class);
        startActivity(intent_Inicio);

    }

     */


}
