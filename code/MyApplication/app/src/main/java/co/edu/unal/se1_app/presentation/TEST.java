package co.edu.unal.se1_app.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.AdminController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TEST extends AppCompatActivity {

    int num=0;
    TextView numero;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        //reset(this.findViewById(android.R.id.content));
        numero= (TextView) findViewById(R.id.textView6);
        Button menos= (Button ) findViewById(R.id.button2);
        Button mas= (Button) findViewById(R.id.button5);

        mas.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        countUP(view);

                    }
                });

        menos.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                       countDOWN(view);
                    }
                });



    }

    public void countUP (View view){
        num++;
        numero.setText(Integer.toString(num));
    }

    public void countDOWN (View view){
        num--;
        numero.setText(Integer.toString(num));
    }

    public void reset (View view){
        num=0;
        numero.setText(Integer.toString(num));
    }

}