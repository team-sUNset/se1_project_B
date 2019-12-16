package co.edu.unal.se1_app.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TEST extends AppCompatActivity {

    private Spinner offices;
    private Spinner implementos;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reserve);
        offices= (Spinner)findViewById(R.id.spinner_offices);
        implementos =(Spinner)findViewById(R.id.spinner_implements);
        ArrayList<String> elementos= new ArrayList<String>();
        elementos.add("El 1");
        elementos.add("El 2");
        elementos.add("El 3");
        elementos.add("El 4");
        ArrayList<String> oficinas= new ArrayList<String>();
        oficinas.add("Office 1");
        oficinas.add("Office 2");
        oficinas.add("Office 3");
        oficinas.add("Office 4");
        ArrayAdapter adp = new ArrayAdapter(this ,android.R.layout.simple_spinner_item,oficinas);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        offices.setAdapter(adp);
        offices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento= (String) offices.getAdapter().getItem(position);
                Toast.makeText(TEST.this ,"Seleccionaste Oficina"+ elemento,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter adp2 = new ArrayAdapter(TEST.this ,android.R.layout.simple_spinner_item,elementos);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        implementos.setAdapter(adp2);
        implementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento= (String) implementos.getAdapter().getItem(position);
                Toast.makeText(TEST.this ,"Seleccionaste"+ elemento,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Long user = MainActivity.main_user();
        //System.out.println(user);


    }
}