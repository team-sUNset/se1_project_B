package co.edu.unal.se1_app.presentation.ui.history;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.businessLogic.controller.SpaceController;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveListCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.presentation.MainActivity;
import co.edu.unal.se1_app.presentation.ui.inventary.AdapterItemInventary;

import java.util.ArrayList;
import java.util.List;

public class historyFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.balon);

        ReserveController reserveController = new ReserveController();
        EquipmentController equipmentController = new EquipmentController();
        SpaceController spaceController = new SpaceController();
        reserveController.getReservesByStudentId(MainActivity.main_user(), new ReserveListCallback() {
            @Override
            public void onSuccess(@NonNull List<Reserve> reserves) {
                spaceController.getSpaces(new SpaceListCallback() {
                    @Override
                    public void onSuccess(@NonNull List<Space> spaces) {
                        equipmentController.getEquipment(new EquipmentListCallback() {
                            @Override
                            public void onSuccess(@NonNull List<Equipment> equipment) {
                                ArrayList<Category> category = new ArrayList<Category>();
                                for( Reserve rv : reserves ){
                                    if( rv.isType() == false ){
                                        for( Equipment eq : equipment ){
                                            if( !eq.getId().equals( rv.getElementId() ) ) continue;
                                            category.add( new Category( eq.getId().toString() ,
                                                    eq.getName() , "Implemento - OfficeID: "
                                                    + eq.getOfficeID().toString(), dr) );
                                        }
                                    }else{
                                        for( Space sp : spaces ){
                                            if( !sp.getId().equals( rv.getElementId() ) ) continue;
                                            category.add( new Category( sp.getId().toString() ,
                                                    sp.getName() , "Espacio", dr) );
                                        }
                                    }
                                }
                                ListView lv = (ListView) root.findViewById(R.id.listView);
                                AdapterItemInventary adapter = new AdapterItemInventary(getActivity(), category);
                                lv.setAdapter(adapter);
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                System.out.println("Activity Historial: " + throwable.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        System.out.println("Activity Historial: " + throwable.getMessage());
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Activity Historial: " + throwable.getMessage());
            }
        });

        return root;
    }
}