package co.edu.unal.se1_app.presentation.ui.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.OfficeController;

public class deleteOfficeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_delete_office, container, false);
        Button boton = (Button) root.findViewById(R.id.button7);
        final EditText idOffice= (EditText) root.findViewById(R.id.deleteOffice);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Long  office= Long.parseLong(idOffice.getText().toString());

                        OfficeController officeController= new OfficeController();
                        officeController.deleteOffice(office);
                        System.out.println(office);

                    }
                });

        return root;
    }
}