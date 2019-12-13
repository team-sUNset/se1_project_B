package com.example.myapplication.ui.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class createFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create);
        Button boton = (Button)findViewById(R.id.button7);
        final EditText name= (EditText) findViewById(R.id.createUser);
        final EditText pass= (EditText) findViewById(R.id.createPass);
        final EditText mail= (EditText) findViewById(R.id.createMail);
        final EditText lastName= (EditText) findViewById(R.id.createLastName);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        String usuario= name.getText().toString();
                        String password= pass.getText().toString();
                        String email= mail.getText().toString();
                        String apellido= lastName.getText().toString();
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Usuario: "+usuario, Toast.LENGTH_SHORT);

                        toast1.show();
                        System.out.println(usuario);
                        System.out.println(password);

                    }
                });






    }


}