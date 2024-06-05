package lt.nerimantas.springboot_rest_api.controller;


import lt.nerimantas.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    //public Student getStudent(){                                          //without ResponseEntity class
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Vardenis", "Pavardenis");
        //return student;                                                   //without ResponseEntity class
        //return new ResponseEntity<>(student, HttpStatus.OK);              //with ResponseEntity class V1
        //return ResponseEntity.ok(student);                                //with ResponseEntity class V2
        return ResponseEntity.ok().header("custom-header", "Nerimantas")
                .body(student);                                             //with ResponseEntity class V3
    }

    // http://localhost:8080/students
    @GetMapping
    //public List<Student> getStudents(){                                   //without ResponseEntity class
    public ResponseEntity<List<Student>> getStudents(){                     //with ResponseEntity class
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Vardenis", "Pavardenis"));
        students.add(new Student(2, "Vardis", "Pavardis"));
        students.add(new Student(3, "Vardenė", "Pavardenė"));
        //return students;                                                  //without ResponseEntity class
        return ResponseEntity.ok(students);                                 //with ResponseEntity class
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/Herkus/Mantas
    @GetMapping("{id}/{first-name}/{last-name}")
    //public Student studentPathVariable(@PathVariable int id,                      //without ResponseEntity class
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id,        //with ResponseEntity class
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName
    ){
    //return new Student(id,firstName, lastName);                                   //without ResponseEntity class
    Student student = new Student(id,firstName, lastName);                          //with ResponseEntity class
        return ResponseEntity.ok(student);                                          //with ResponseEntity class
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Herkus&lastName=Mantas
    @GetMapping("query")
    //public Student studentRequestVariable(@RequestParam int id,                   //without ResponseEntity class
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,     //with ResponseEntity class
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        //return new Student(id, firstName, lastName);                              //without ResponseEntity class
        Student student = new Student(id, firstName, lastName);                     //with ResponseEntity class
        return ResponseEntity.ok(student);                                          //with ResponseEntity class
    }

    // Spring BOOT REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("create")
    // @ResponseStatus(HttpStatus.CREATED)                                          //without ResponseEntity class
    //public Student createStudent(@RequestBody Student student){                   //without ResponseEntity class
    public ResponseEntity<Student> createStudent(@RequestBody Student student){     //with ResponseEntity class
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;                                                           //without ResponseEntity class
        return new ResponseEntity<>(student, HttpStatus.CREATED);                   //with ResponseEntity class

    }

    // Spring BOOT REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    //public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){                //without ResponseEntity class
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){  //with ResponseEntity class
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;                                                                                           //without ResponseEntity class
        return  ResponseEntity.ok(student);                                                                         //with ResponseEntity class
    }

    // Spring Boot REST APi that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){     //with ResponseEntity class
    //public String deleteStudent(@PathVariable ("id") int studentId){                  //without ResponseEntity class
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully");                       //with ResponseEntity class
        //return "Student deleted successfully";                                        //without ResponseEntity class

    }
}
