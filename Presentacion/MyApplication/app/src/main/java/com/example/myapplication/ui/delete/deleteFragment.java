package com.example.myapplication.ui.delete;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.MainMenuUser;
import com.example.myapplication.R;

public class deleteFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_delete, container, false);
        Button button1= (Button) root.findViewById(R.id.b_user);
        Button button2= (Button) root.findViewById(R.id.b_space);
        Button button3= (Button) root.findViewById(R.id.b_office);
        button1.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                       deleteUserFragment del = new deleteUserFragment();
                        FragmentManager manager= getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,del,del.getTag()).commit();

                    }
                });

        button2.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        deleteSpaceFragment del = new deleteSpaceFragment();
                        FragmentManager manager= getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,del,del.getTag()).commit();

                    }
                });
        button3.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        deleteOfficeFragment del = new  deleteOfficeFragment();
                        FragmentManager manager= getFragmentManager();
                        manager.beginTransaction().replace(R.id.nav_host_fragment,del,del.getTag()).commit();

                    }
                });

        return root;
    }






}