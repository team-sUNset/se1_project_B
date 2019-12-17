package co.edu.unal.se1_app.presentation.ui.delete;

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
import co.edu.unal.se1_app.businessLogic.controller.StudentController;

public class deleteUserFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_delete_user, container, false);
        Button boton = (Button) root.findViewById(R.id.button7);
        final EditText idOffice= (EditText) root.findViewById(R.id.deleteUser);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        Long user= Long.parseLong(idOffice.getText().toString());
                        StudentController studentController= new StudentController();
                        studentController.deleteStudent(user);
                        Toast toast1 =
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Usuario Eliminado" , Toast.LENGTH_SHORT);
                        toast1.show();
                        System.out.println(user);

                    }
                });

        return root;
    }
}