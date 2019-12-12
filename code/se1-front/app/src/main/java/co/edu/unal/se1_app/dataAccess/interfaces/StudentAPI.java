package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Student;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentAPI {

    @GET("students")
    Call < List<Student> > getStudents();
}
