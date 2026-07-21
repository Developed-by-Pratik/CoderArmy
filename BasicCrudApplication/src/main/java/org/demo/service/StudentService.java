package org.demo.service;

import org.demo.entity.Student;
import org.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        System.out.println("saveStudent : " +  student);
        student.setDeleted(false);
        return studentRepository.save(student);
    }

    public List<Student> readAllStudents() {
        System.out.println("readAllStudents where is Deleted is false");
        return studentRepository.findByIsDeletedFalse();
    }

    public Student readStudentById(Long id) {
        System.out.println("readStudentById : Fetching student with ID: " + id);
        Student student = studentRepository.findById(id).orElse(null);
        
        if (student != null && student.isDeleted()) {
            System.out.println("readStudentById : Student with ID: " + id + " is deleted - returning null");
            return null;
        }
        
        if (student == null) {
            System.out.println("readStudentById : Student not found with ID: " + id);
        } else {
            System.out.println("readStudentById : Student found: " + student);
        }
        return student;
    }

    public Student updateStudentById(Long id, Student student) {
        System.out.println("updateStudentById : Fetching student with ID: " + id);
        Student existingStudent = readStudentById(id);

        if (existingStudent == null) {
            System.out.println("updateStudentById : Student not found with ID: " + id);
            return null;
        }

        if (existingStudent.isDeleted()) {
            System.out.println("updateStudentById : Cannot update - Student is deleted. ID: " + id);
            return null;
        }

        if (student.isDeleted()) {
            System.out.println("updateStudentById : Cannot update - Input student is marked as deleted");
            return null;
        }

        System.out.println("updateStudentById : Updating student fields for ID: " + id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());
        existingStudent.setSubject(student.getSubject());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setDeleted(false);

        Student updatedStudent = studentRepository.save(existingStudent);
        System.out.println("updateStudentById : Student updated successfully: " + updatedStudent);
        return updatedStudent;
    }

    public Student deleteStudentById(Long id) {
        System.out.println("deleteStudentById (HARD DELETE) : Attempting to delete student with ID: " + id);
        Student student = studentRepository.findById(id).orElse(null);
        
        if (student == null) {
            System.out.println("deleteStudentById : Student not found with ID: " + id);
            return null;
        }
        
        if (student.isDeleted()) {
            System.out.println("deleteStudentById : Student with ID: " + id + " is already soft-deleted");
            return null;
        }
        
        System.out.println("deleteStudentById : Hard deleting student: " + student);
        studentRepository.delete(student);
        System.out.println("deleteStudentById : Student permanently deleted from database");
        return student;
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public boolean deleteStudentSoftly(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        System.out.println("deleteStudentSoftly : " +  student);
        if (student == null) {
            return false;
        }
        student.setDeleted(true);
        studentRepository.save(student);
        System.out.println("deleteStudentSoftly : " +  student);
        return true;
    }

}