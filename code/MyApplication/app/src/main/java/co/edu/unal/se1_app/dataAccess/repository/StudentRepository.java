package co.edu.unal.se1_app.dataAccess.repository;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.callback.StudentListCallback;
import co.edu.unal.se1_app.dataAccess.config.Config;
import co.edu.unal.se1_app.dataAccess.interfaces.StudentAPI;
import co.edu.unal.se1_app.dataAccess.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentRepository {

    private Retrofit retrofit;

    public StudentRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getStudents( @Nullable StudentListCallback callbacks ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<List<Student>> call = studentAPI.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void getStudentById( Long id , @Nullable StudentCallback callbacks  ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.getStudentById( id );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void createStudent( Student student , @Nullable StudentCallback callbacks ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.createStudent( student );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void updateStudent( Long id , Student student , @Nullable StudentCallback callbacks  ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.updateStudent( id , student );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void deleteStudent( Long id ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Void> call = studentAPI.deleteStudent( id );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println( "Code: " + response.code() + "\n" );
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return;
    }

}
