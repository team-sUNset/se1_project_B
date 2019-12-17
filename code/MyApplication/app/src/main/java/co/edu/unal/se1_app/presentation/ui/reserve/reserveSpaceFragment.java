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

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.businessLogic.controller.SpaceController;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.OfficeListCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.presentation.MainActivity;

public class reserveSpaceFragment extends Fragment {

    private Spinner s_offices;

    Long spID = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reserve_space, container, false);
        s_offices = (Spinner)root.findViewById(R.id.spinner_offices);


        ArrayList<String> espacios = new ArrayList<String>();
        ArrayList<Long> espaciosID = new ArrayList<Long>();

        SpaceController spaceController = new SpaceController();
        ReserveController reserveController = new ReserveController();

        spaceController.getSpaces(new SpaceListCallback() {
            @Override
            public void onSuccess(@NonNull List<Space> spaces) {
                for( Space sp : spaces ){
                    espacios.add( sp.getName() );
                    espaciosID.add( sp.getId() );
                }

                ArrayAdapter adp = new ArrayAdapter(getActivity() ,android.R.layout.simple_spinner_item,espacios);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s_offices.setAdapter(adp);
                s_offices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spID = espaciosID.get(position);
                        Toast.makeText(getActivity() ,"Seleccionaste : "+ espacios.get(position),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spID = null;
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Message Crear Reserva: " + throwable.getMessage());
            }
        });

        Long user = MainActivity.main_user();
        final EditText startD = (EditText) root.findViewById(R.id.dayStart);
        final EditText startT = (EditText) root.findViewById(R.id.timeStart);
        final Button boton = (Button) root.findViewById(R.id.reservar);

        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if( spID == null ) {
                            Toast toast1 =
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Seleccione un Espacio", Toast.LENGTH_SHORT);
                            toast1.show();
                        }

                        String startDateTime = startD.getText().toString()+" "+startT.getText().toString();

                        ReserveController reserveController = new ReserveController();
                        Reserve reserve = new Reserve(true, user, spID ,startDateTime,"X");
                        reserveController.createReserve(reserve,new ReserveCallback() {
                            @Override
                            public void onSuccess(@NonNull Reserve reserve) {
                                //System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Reserva Creada" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Error: No se puede crear reserva\n" + throwable.getMessage() , Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });
                    }
                });

        return root;
    }
}