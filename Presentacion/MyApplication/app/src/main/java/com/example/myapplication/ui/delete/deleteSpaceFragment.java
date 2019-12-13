package com.example.myapplication.ui.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class deleteSpaceFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_delete_space, container, false);
        Button boton = (Button) root.findViewById(R.id.button7);
        final EditText idOffice= (EditText) root.findViewById(R.id.deleteSpace);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String office= idOffice.getText().toString();
                        System.out.println(office);

                    }
                });

        return root;
    }
}