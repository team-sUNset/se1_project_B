package co.edu.unal.se1_app.presentation.ui.office;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;
import co.edu.unal.se1_app.dataAccess.callback.OfficeListCallback;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.presentation.ui.history.Category;

import java.util.ArrayList;
import java.util.List;

public class officeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_office, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.oficina);

        OfficeController officeController = new OfficeController();
        officeController.getOffices(new OfficeListCallback() {
            @Override
            public void onSuccess(@NonNull List<Office> offices) {
                ArrayList<Category> category = new ArrayList<Category>();
                for( Office of : offices ){
                    category.add( new Category( of.getId().toString() , of.getName() ,
                            "id: " + of.getId().toString() , dr ) );
                }

                ListView lv = (ListView) root.findViewById(R.id.listView);
                AdapterItemOffice adapter = new AdapterItemOffice(getActivity(), category);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Activity Oficinas: " + throwable.getMessage());
            }
        });

        return root;
    }
}