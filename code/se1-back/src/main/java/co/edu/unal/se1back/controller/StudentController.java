package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.model.Student;
import co.edu.unal.se1back.repository.StudentRepository;
import co.edu.unal.se1back.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by riperezro on 24/11/19.
 */
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable(value = "id") Long studentId,
                           @Valid @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        student.setPassword(studentDetails.getPassword());
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }
}
