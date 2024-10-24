package com.example.demo.Controller;

import com.example.demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students=new ArrayList<>(List.of(
          new Student(1,"jenushan",60),
           new Student(2,"kaviraj",89)

    ));
    @GetMapping("/students")
    public List<Student> getStudents(){
return students;
    }
    /*
    we have insert springboot security that time csrf token is very important that time because it will need any changes in the
    web csrf token need
     */

    //token creation
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}
