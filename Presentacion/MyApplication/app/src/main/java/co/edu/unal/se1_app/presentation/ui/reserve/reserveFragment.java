package co.edu.unal.se1_app.presentation.ui.reserve;

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

public class reserveFragment extends Fragment {

    private Spinner offices;
    private Spinner implementos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reserve, container, false);
        offices= (Spinner)root.findViewById(R.id.spinner_offices);
        implementos =(Spinner)root.findViewById(R.id.spinner_implements);
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

        final String[] elemento = new String[1];
        ArrayAdapter adp2 = new ArrayAdapter(this.getActivity() ,android.R.layout.simple_spinner_item,elementos);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        implementos.setAdapter(adp2);
        implementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                elemento[0] = (String) implementos.getAdapter().getItem(position);
                Toast.makeText(getActivity() ,"Seleccionaste: "+ elemento[0],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Long user = MainActivity.main_user();
        final EditText startD= (EditText) root.findViewById(R.id.dayStart);
        final EditText endD= (EditText) root.findViewById(R.id.dayEnd);
        final EditText startT= (EditText) root.findViewById(R.id.timeStart);
        final EditText endT= (EditText) root.findViewById(R.id.timeEnd);
        final Button boton= (Button) root.findViewById(R.id.reservar);



        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String startDateTime= startD.getText().toString()+startT.getText().toString();
                        String endDateTime= endD.getText().toString()+endT.getText().toString();
                        Long elem= Long.parseLong("6");

                        ReserveController reserveController = new ReserveController();
                        Reserve reserve= new Reserve(false, user,elem,startDateTime,endDateTime);
                        reserveController.createReserve(reserve,new ReserveCallback() {
                            @Override
                            public void onSuccess(@NonNull Reserve reserve) {
                                //System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Reserva Creada " , Toast.LENGTH_SHORT);
                                toast1.show();
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Error: No se puede crear reserva" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });
                    }
                });









        return root;
    }
}