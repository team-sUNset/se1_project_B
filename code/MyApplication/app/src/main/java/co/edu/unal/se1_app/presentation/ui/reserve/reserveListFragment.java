package co.edu.unal.se1_app.presentation.ui.reserve;
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
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.businessLogic.controller.SpaceController;
import co.edu.unal.se1_app.businessLogic.controller.StudentController;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveListCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
import co.edu.unal.se1_app.dataAccess.callback.StudentListCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.presentation.ui.history.Category;

public class reserveListFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reserve_list, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.user);

        ReserveController reserveController = new ReserveController();
        EquipmentController equipmentController = new EquipmentController();
        SpaceController spaceController = new SpaceController();
        reserveController.getReserves(new ReserveListCallback() {
            @Override
            public void onSuccess(@NonNull List<Reserve> reserves) {
                equipmentController.getEquipment(new EquipmentListCallback() {
                    @Override
                    public void onSuccess(@NonNull List<Equipment> equipment) {
                        spaceController.getSpaces(new SpaceListCallback() {
                            @Override
                            public void onSuccess(@NonNull List<Space> spaces) {
                                ArrayList<Category> category = new ArrayList<Category>();
                                for (Reserve rv : reserves) {
                                    if (rv.isType() == false) {
                                        for (Equipment eq : equipment) {
                                            if (!eq.getId().equals(rv.getElementId())) continue;
                                            category.add(new Category(eq.getId().toString(),
                                                    eq.getName(), "Implemento - ReserveID: "
                                                    + rv.getId().toString() + " - StudentID: " +
                                                    rv.getStudentId().toString(), dr));
                                        }
                                    } else {
                                        for (Space sp : spaces) {
                                            if (!sp.getId().equals(rv.getElementId())) continue;
                                            category.add(new Category(sp.getId().toString(),
                                                    sp.getName(), "Espacio - ReserveID: "
                                                    + rv.getId().toString() + " - StudentID: " +
                                                    rv.getStudentId().toString(), dr));
                                        }
                                    }
                                    ListView lv = (ListView) root.findViewById(R.id.listView);
                                    AdapterItemReserveList adapter = new AdapterItemReserveList(getActivity(), category);
                                    lv.setAdapter(adapter);
                                }
                            }
                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                System.out.println("Message: " + throwable);
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        System.out.println("Message: " + throwable);
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Message: " + throwable);
            }
        });

        return root;
    }
}