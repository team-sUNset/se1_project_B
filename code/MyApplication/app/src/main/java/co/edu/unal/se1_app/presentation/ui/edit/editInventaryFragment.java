package co.edu.unal.se1_app.presentation.ui.edit;

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

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.OfficeListCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.presentation.MainActivity;

public class editInventaryFragment extends Fragment {


    private Spinner s_implementos;
    Long eqID = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_edit_inventary, container, false);

        s_implementos =(Spinner)root.findViewById(R.id.spinner_implements);
        Button editar = (Button) root.findViewById(R.id.edit);
        Button delete = (Button) root.findViewById(R.id.deleteImplement);

        ArrayList<String> elementos = new ArrayList<String>();
        ArrayList<Long> elementosID = new ArrayList<Long>();



        EquipmentController equipmentController = new EquipmentController();
        ReserveController reserveController = new ReserveController();

        equipmentController.getEquipment(new EquipmentListCallback() {
            @Override
            public void onSuccess(@NonNull List<Equipment> equipment) {
                for( Equipment eq : equipment ){
                    elementos.add( eq.getName() + " - OfficeID " + eq.getOfficeID().toString() );
                    elementosID.add( eq.getId() );
                }

                ArrayAdapter adp = new ArrayAdapter(getActivity() ,android.R.layout.simple_spinner_item,elementos);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s_implementos.setAdapter(adp);
                s_implementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        eqID = elementosID.get(position);
                        Toast.makeText(getActivity() ,"Seleccionaste : "+ elementos.get(position),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        eqID = null;
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Message: " + throwable.getMessage());
            }
        });

        final EditText cantidad= (EditText) root.findViewById(R.id.quantity);

        editar.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        int stock = Integer.valueOf(cantidad.getText().toString());
                        equipmentController.setStock(eqID, stock, new EquipmentCallback() {
                                    @Override
                                    public void onSuccess(@NonNull Equipment equipment) {
                                        Toast toast1 =
                                                Toast.makeText(getActivity().getApplicationContext(),
                                                        "Editaste el elemento" , Toast.LENGTH_SHORT);
                                        toast1.show();

                                    }

                                    @Override
                                    public void onError(@NonNull Throwable throwable) {
                                        Toast toast1 =
                                                Toast.makeText(getActivity().getApplicationContext(),
                                                        "No se puede editar" , Toast.LENGTH_SHORT);
                                        toast1.show();
                                    }
                                }
                        );



                    }
                });


        delete.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        equipmentController.deleteEquipment(eqID);
                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Elemento eliminado" , Toast.LENGTH_SHORT);
                        toast1.show();




                    }
                });



        return root;
    }
}