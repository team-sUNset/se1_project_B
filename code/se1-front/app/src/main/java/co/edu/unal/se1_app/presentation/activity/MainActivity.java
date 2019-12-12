package co.edu.unal.se1_app.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.dataAccess.interfaces.StudentAPI;
import co.edu.unal.se1_app.dataAccess.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public TextView textViewResult;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

         retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         deleteStudent();

        StudentAPI studentAPI = retrofit.create(StudentAPI.class);

        Call<List<Student>> call = studentAPI.getStudents(  );

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if( !response.isSuccessful() ){
                    textViewResult.setText( "Code: " + response.code() );
                    return;
                }
                List <Student> students = response.body();
                for( Student s : students ){
                    String content = "";
                    content += "id: " + s.getId() + "\n";
                    content += "password: " + s.getPassword() + "\n";
                    content += "firstName: " + s.getFirstName() + "\n";
                    content += "lastName: " + s.getLastName() + "\n";
                    content += "email: " + s.getEmail() + "\n\n";
                    textViewResult.append( content );
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                textViewResult.setText("Message : " + t.getMessage());
            }
        });
    }

    public void createStudent(){
        Student s = new Student( "1234" , "Esteban" , "Reyes" , "jereyesca@unal.edu.co" );
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.createStudent( s );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    textViewResult.setText( "Code: " + response.code() );
                    return;
                }
                Student sal = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "id: " + sal.getId() + "\n";
                content += "password: " + sal.getPassword() + "\n";
                content += "firstName: " + sal.getFirstName() + "\n";
                content += "lastName: " + sal.getLastName() + "\n";
                content += "email: " + sal.getEmail() + "\n\n";
                textViewResult.append( content );
                textViewResult.append( "----- OUTPUT ----- \n\n" );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                textViewResult.setText("Message Post : " + t.getMessage());
            }
        });
    }

    public void updateStudent(){
        Student s = new Student( "abcd" , "Esteban" , "Reyes" , "jereyesca@unal.edu.co" );
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.updateStudent( Long.valueOf(3) , s );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    textViewResult.setText( "Code: " + response.code() );
                    return;
                }
                Student sal = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "id: " + sal.getId() + "\n";
                content += "password: " + sal.getPassword() + "\n";
                content += "firstName: " + sal.getFirstName() + "\n";
                content += "lastName: " + sal.getLastName() + "\n";
                content += "email: " + sal.getEmail() + "\n\n";
                textViewResult.append( content );
                textViewResult.append( "----- UPDATED ----- \n\n" );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                textViewResult.setText("Message Put : " + t.getMessage());
            }
        });
    }

    public void deleteStudent(){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Void> call = studentAPI.deleteStudent( Long.valueOf(2) );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.append( "Code: " + response.code() );
                textViewResult.append( "----- DELETED ----- \n\n" );
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText("Message Delete : " + t.getMessage());
            }
        });
    }

}
