package co.edu.unal.se1_app.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.se1_app.R;

import co.edu.unal.se1_app.businessLogic.controller.AdminController;
import co.edu.unal.se1_app.businessLogic.controller.StudentController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.presentation.ui.create.createFragment;

public class MainActivity extends AppCompatActivity {

   public static Long usuario;

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
                       usuario = Long.parseLong(text_user.getText().toString());
                       String password= text_pass.getText().toString();

                       //open_test(view);

                        StudentController studentController = new StudentController();
                        studentController.verifyStudent(usuario, password,new StudentCallback() {
                            @Override
                            public void onSuccess(@NonNull Student student) {
                                //System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Iniciando Sesión " , Toast.LENGTH_SHORT);
                                toast1.show();
                                open_user(view);
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Error: Usurio o contraseña incorrecta" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }


                        });

                    }
                });

        boton2.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Long  admin= Long.parseLong(text_user.getText().toString());
                        String password= text_pass.getText().toString();



                        AdminController adminController = new AdminController();
                        adminController.verifyAdmin(admin, password,new AdminCallback() {
                            @Override
                            public void onSuccess(@NonNull Admin admin) {
                                //System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Iniciando Sesión " , Toast.LENGTH_SHORT);
                                toast1.show();
                                open_admin(view);
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Error: Usurio o contraseña incorrecta" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });
                    }
                });





    }

    public static Long main_user(){

        return usuario;
    }

    public void open_test(View view) {
        Intent intent_Inicio = new Intent(this, TEST.class);
        startActivity(intent_Inicio);

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
