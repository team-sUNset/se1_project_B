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
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.OfficeListCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.presentation.MainActivity;
import co.edu.unal.se1_app.presentation.MainMenuUser;

import java.util.ArrayList;
import java.util.List;

public class createImplementFragment extends Fragment {

    private Spinner s_offices;
    private Long ofID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_implemento, container, false);
        s_offices = (Spinner)root.findViewById(R.id.spinner_offices);
        Button crear= (Button) root.findViewById(R.id.crearImplemento);
        EditText e_nombre= (EditText) root.findViewById(R.id.editText4);
        EditText e_quantity= (EditText) root.findViewById(R.id.quantity);

        OfficeController officeController = new OfficeController();

        ArrayList<String> oficinas = new ArrayList<>();
        ArrayList<Long> oficinasID = new ArrayList<>();
        ofID = null;
        officeController.getOffices(new OfficeListCallback() {
            @Override
            public void onSuccess(@NonNull List<Office> offices) {
                for( Office of : offices ){
                    oficinas.add( of.getName() );
                    oficinasID.add( of.getId() );
                }

                ArrayAdapter adp = new ArrayAdapter(getActivity() ,android.R.layout.simple_spinner_item,oficinas);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s_offices.setAdapter(adp);
                s_offices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ofID = oficinasID.get(position);
                        Toast.makeText(getActivity() ,"Seleccionaste : "+oficinas.get(position),Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        ofID = null;
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Message Crear Implemento" + throwable.getMessage());
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = e_nombre.getText().toString();
                int cantidad = Integer.valueOf(e_quantity.getText().toString());

                Equipment equipment = new Equipment( nombre , ofID , cantidad );

                EquipmentController equipmentController = new EquipmentController();
                equipmentController.createEquipment(equipment, new EquipmentCallback() {
                    @Override
                    public void onSuccess(@NonNull Equipment equipment) {
                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Implemento Creado " , Toast.LENGTH_SHORT);
                        toast1.show();

                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Error " , Toast.LENGTH_SHORT);
                        toast1.show();
                        System.out.println("Message Crear Implemento" + throwable.getMessage());
                    }
                });

            }
        });

        return root;
    }
}