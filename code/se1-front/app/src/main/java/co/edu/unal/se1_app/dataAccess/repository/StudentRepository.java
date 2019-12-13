package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.interfaces.StudentAPI;
import co.edu.unal.se1_app.dataAccess.model.Student;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentRepository {

    private Retrofit retrofit;
    private List<Student> returnList;
    private Student returnObject;

    public StudentRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public List<Student> getStudents(){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<List<Student>> call = studentAPI.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnList = response.body();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnList;
    }

    public Student getStudentById( Long id ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.getStudentById( id );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Student createStudent( Student student ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.createStudent( student );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Student updateStudent( Long id , Student student ){
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<Student> call = studentAPI.updateStudent( id , student );
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
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
