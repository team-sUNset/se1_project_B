package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentAPI {

    @GET("students")
    Call < List<Student> > getStudents();

    @GET("students/{id}")
    Call < Student > getStudentById(@Path("id") Long id);

    @POST("students")
    Call < Student > createStudent (@Body Student student);

    @PUT("students/{id}")
    Call < Student > updateStudent (@Path("id") Long id , @Body Student student);

    @DELETE("students/{id}")
    Call <Void> deleteStudent(@Path("id") Long id );
}
