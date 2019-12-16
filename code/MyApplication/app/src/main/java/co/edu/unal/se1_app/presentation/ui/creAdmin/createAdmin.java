package co.edu.unal.se1_app.presentation.ui.creAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import co.edu.unal.se1_app.R;

public class createAdmin extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_admin, container, false);

        Button button2= (Button) root.findViewById(R.id.c_space);
        Button button3= (Button) root.findViewById(R.id.c_office);
        button2.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        createSpaceFragment del = new createSpaceFragment();
                        FragmentManager manager= getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,del,del.getTag()).commit();
                    }
                });
        button3.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        createOfficeFragment del = new createOfficeFragment();
                        FragmentManager manager= getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,del,del.getTag()).commit();
                    }
                });


        return root;
    }
}