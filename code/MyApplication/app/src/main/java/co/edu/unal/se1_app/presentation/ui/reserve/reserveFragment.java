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
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
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

public class reserveFragment extends Fragment {

    private Spinner s_offices;
    private Spinner s_implementos;
    Long eqID = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reserve, container, false);
        s_offices = (Spinner)root.findViewById(R.id.spinner_offices);
        s_implementos =(Spinner)root.findViewById(R.id.spinner_implements);

        ArrayList<String> elementos = new ArrayList<String>();
        ArrayList<Long> elementosID = new ArrayList<Long>();
        ArrayList<String> oficinas = new ArrayList<String>();
        ArrayList<Long> oficinasID = new ArrayList<Long>();

        OfficeController officeController = new OfficeController();
        EquipmentController equipmentController = new EquipmentController();
        ReserveController reserveController = new ReserveController();

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
                        Toast.makeText(getActivity() ,"Seleccionaste : "+ oficinas.get(position),Toast.LENGTH_SHORT).show();
                        officeController.equipmentInOfice( oficinasID.get(position), new EquipmentListCallback() {
                            @Override
                            public void onSuccess(@NonNull List<Equipment> equipment) {
                                elementos.clear();
                                for( Equipment eq : equipment ){
                                    elementos.add( eq.getName() );
                                    elementosID.add( eq.getId() );
                                }

                                ArrayAdapter adp2 = new ArrayAdapter(getActivity() ,android.R.layout.simple_spinner_item,elementos);
                                adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                s_implementos.setAdapter(adp2);
                                s_implementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        eqID = elementosID.get( position );
                                        Toast.makeText(getActivity() ,"Seleccionaste: "+ elementos.get(position),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        eqID = null;
                                    }
                                });
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                System.out.println("Message Crear Reserva: " + throwable.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        eqID = null;
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

                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Seleccione Oficina e Implemento" , Toast.LENGTH_SHORT);
                        toast1.show();

                        String startDateTime= startD.getText().toString()+" "+startT.getText().toString();

                        ReserveController reserveController = new ReserveController();
                        Reserve reserve = new Reserve(false, user, eqID ,startDateTime,"X");
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