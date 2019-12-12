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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI studentAPI = retrofit.create(StudentAPI.class);

        Call<List<Student>> call = studentAPI.getStudents();

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


}
