package co.edu.unal.se1_app.presentation.ui.creAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.AdminController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.presentation.MainActivity;
import co.edu.unal.se1_app.presentation.MainMenuUser;

import java.util.ArrayList;

public class createImplementFragment extends Fragment {

    private Spinner offices;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_implemento, container, false);
        offices= (Spinner)root.findViewById(R.id.spinner_offices);

        ArrayList<String> oficinas= new ArrayList<String>();

        ArrayAdapter adp = new ArrayAdapter(this.getActivity() ,android.R.layout.simple_spinner_item,oficinas);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        offices.setAdapter(adp);
        final String[] oficina = new String[1];
        offices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oficina[0] = (String) offices.getAdapter().getItem(position);
                Toast.makeText(getActivity() ,"Seleccionaste : "+ oficina[0],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return root;
    }
}