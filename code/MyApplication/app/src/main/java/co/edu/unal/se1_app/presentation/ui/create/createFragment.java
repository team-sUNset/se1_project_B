package co.edu.unal.se1_app.presentation.ui.create;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.StudentController;
import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.model.Student;

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

                        StudentController studentController = new StudentController();
                        Student newStudent = new Student( password , usuario , apellido , email );
                        studentController.createStudent(newStudent, new StudentCallback() {
                            @Override
                            public void onSuccess(@NonNull Student student) {
                                System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Usuario: "+ student.getId().toString() , Toast.LENGTH_SHORT);
                                toast1.show();
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getApplicationContext(),
                                                "Error" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });
                    }
                });






    }


}