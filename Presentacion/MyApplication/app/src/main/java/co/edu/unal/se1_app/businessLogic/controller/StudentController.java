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

    public void createStudent(Student student , @Nullable StudentCallback callbacks){
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

    public Student getStudentById(Long id){
        studentRepository = new StudentRepository();
        return studentRepository.getStudentById( id );
    }

    public boolean verifyStudent( Long id , String password ){
        studentRepository = new StudentRepository();
        Student student = studentRepository.getStudentById( id );
        if( student == null ) return false;
        return password.equals( student.getPassword() );
    }

}
