package co.edu.unal.se1_app.businessLogic.controller;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.dataAccess.repository.StudentRepository;

public class StudentController {

    StudentRepository studentRepository;
    private List<Student> returnList;
    private Student returnObject;

    public StudentController() {

    }

    public void createStudent( Student student , @Nullable StudentCallback callbacks ){
        studentRepository = new StudentRepository();
        studentRepository.createStudent(student, new StudentCallback() {
            @Override
            public void onSuccess(@NonNull Student student) {
                callbacks.onSuccess(student);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError(throwable);
            }
        });
    }

    public void getStudentById( Long id , @Nullable StudentCallback callbacks ){
        studentRepository = new StudentRepository();
        studentRepository.getStudentById(id, new StudentCallback() {
            @Override
            public void onSuccess(@NonNull Student student) {
                callbacks.onSuccess( student );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void verifyStudent( Long id , String password , @Nullable StudentCallback callbacks ){
        studentRepository = new StudentRepository();
        studentRepository.getStudentById(id, new StudentCallback() {
            @Override
            public void onSuccess(@NonNull Student student) {
                if( password.equals( student.getPassword() ) ) callbacks.onSuccess( student );
                else callbacks.onSuccess( null );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

}
