package co.edu.unal.se1_app.presentation.ui.cancel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.ReserveController;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;

public class cancelFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cancel, container, false);
        final EditText idPrestamo = (EditText) root.findViewById(R.id.editText3);
        Button confirm = (Button) root.findViewById(R.id.button7);
        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Long id = Long.parseLong(idPrestamo.getText().toString());

                        ReserveController reserveController= new ReserveController();
                        reserveController.deleteReserve(id );
                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Prestamo Cancelado " , Toast.LENGTH_SHORT);
                        toast1.show();

                    }
                }
        );
        return root;
    }
}