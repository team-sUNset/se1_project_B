package co.edu.unal.se1_app.presentation.ui.inventary;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.EquipmentController;
import co.edu.unal.se1_app.businessLogic.controller.SpaceController;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.presentation.ui.history.Category;

public class inventaryFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inventary, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.balon);

        EquipmentController equipmentController = new EquipmentController();
        SpaceController spaceController = new SpaceController();
        equipmentController.getEquipment(new EquipmentListCallback() {
            @Override
            public void onSuccess(@NonNull List<Equipment> equipment) {
                spaceController.getSpaces(new SpaceListCallback() {
                    @Override
                    public void onSuccess(@NonNull List<Space> spaces) {
                        ArrayList<Category> category = new ArrayList<Category>();
                        for( Equipment eq : equipment ){
                            category.add( new Category( eq.getId().toString() , eq.getName() ,
                                    "ImplementoID: " + eq.getId().toString()
                                            + "\nOficinaID:" + eq.getOfficeID() , dr ) );
                        }

                        for( Space sp : spaces ){
                            category.add( new Category( sp.getId().toString() , sp.getName() ,
                                    "EspacioID: " + sp.getId().toString() , dr ) );
                        }

                        ListView lv = (ListView) root.findViewById(R.id.listView);
                        AdapterItemInventary adapter = new AdapterItemInventary(getActivity(), category);
                        lv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        System.out.println("Activity Inventario: " + throwable.getMessage());
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Activity Inventario: " + throwable.getMessage());
            }
        });

        return root;
    }
}