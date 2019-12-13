package co.edu.unal.se1_app.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.se1_app.R;

import co.edu.unal.se1_app.presentation.ui.create.createFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Button boton = (Button) findViewById(R.id.button3);
        Button boton2 = (Button) findViewById(R.id.button4);

        final EditText text_user= (EditText) findViewById(R.id.editText);
        final EditText text_pass= (EditText) findViewById(R.id.editText2);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                       String usuario= text_user.getText().toString();
                        String password= text_pass.getText().toString();
                       System.out.println(usuario);
                       System.out.println(password);
                       open_user(view);
                    }
                });

        boton2.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String usuario= text_user.getText().toString();
                        String password= text_pass.getText().toString();
                        System.out.println(usuario);
                        System.out.println(password);
                        open_admin(view);
                    }
                });





    }


    public void open_user(View view) {
        Intent intent_Inicio = new Intent(this, MainMenuUser.class);
        startActivity(intent_Inicio);

    }
    public void open_admin(View view) {
        Intent intent_Inicio = new Intent(this, MainMenuAdmin.class);
        startActivity(intent_Inicio);

    }




    public void open_create(View view) {
        Intent intent_Inicio = new Intent(this, createFragment.class);
        startActivity(intent_Inicio);

    }


}
