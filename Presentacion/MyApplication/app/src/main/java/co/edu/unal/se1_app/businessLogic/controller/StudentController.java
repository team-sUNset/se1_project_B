package co.edu.unal.se1_app.businessLogic.controller;

import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.dataAccess.repository.StudentRepository;

public class StudentController {

    StudentRepository studentRepository;

    public StudentController() {

    }

    public Student createStudent(Student student){
        studentRepository = new StudentRepository();
        return studentRepository.createStudent( student );
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
