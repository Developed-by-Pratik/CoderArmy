package org.demo.controller;

import jakarta.validation.Valid;
import org.demo.entity.Student;
import org.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        System.out.println("createStudent : Received request to create new student");
        System.out.println("createStudent : Input data - Name: " + student.getName() + ", Email: " + student.getEmail() + ", Roll No: " + student.getRollNo());

        Student savedStudent = studentService.saveStudent(student);

        System.out.println("createStudent : Student created successfully with ID: " + savedStudent.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        System.out.println("getAllStudents : Fetching all active (non-deleted) students");

        List<Student> students = studentService.readAllStudents();

        System.out.println("getAllStudents : Retrieved " + students.size() + " active students");

        if (students.isEmpty()) {
            System.out.println("getAllStudents : No active students found in database");
            return ResponseEntity.ok(students);
        }

        System.out.println("getAllStudents : Returning list of students");
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        System.out.println("getStudentById : Fetching student with ID: " + id);
        Student student = studentService.readStudentById(id);

        if (student == null) {
            System.out.println("getStudentById : Student not found or is deleted with ID: " + id);
            return ResponseEntity.notFound().build();
        }

        if (student.isDeleted()) {
            System.out.println("getStudentById : Cannot retrieve - Student is deleted with ID: " + id);
            return ResponseEntity.notFound().build();
        }

        System.out.println("getStudentById : Student retrieved successfully: " + student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        System.out.println("updateStudent : Updating student with ID: " + id);
        System.out.println("updateStudent : Input student data: " + student);
        
        Student updatedStudent = studentService.updateStudentById(id, student);

        if (updatedStudent == null) {
            System.out.println("updateStudent : Update failed - Student not found or is deleted with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        System.out.println("updateStudent : Student updated successfully: " + updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        System.out.println("deleteStudent : Hard deleting student with ID: " + id);
        Student student = studentService.deleteStudentById(id);

        if (student == null) {
            System.out.println("deleteStudent : Cannot delete - Student not found or already deleted with ID: " + id);
            return ResponseEntity.notFound().build();
        }
        
        System.out.println("deleteStudent : Student permanently deleted from database");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-soft")
    public ResponseEntity<Void> deleteStudentSoftly(@RequestParam Long id) {
        System.out.println("deleteStudentSoftly : Soft deleting student with ID: " + id);
        boolean isDeleted = studentService.deleteStudentSoftly(id);

        if (isDeleted) {
            System.out.println("deleteStudentSoftly : Student soft-deleted successfully - marked as inactive");
            return ResponseEntity.ok().build();
        }

        System.out.println("deleteStudentSoftly : Cannot soft-delete - Student not found with ID: " + id);
        return ResponseEntity.notFound().build();
    }

}