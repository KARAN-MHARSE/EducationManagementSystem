package com.aurionpro.ems.dao;

import java.util.List;
import com.aurionpro.ems.model.Student;
import com.aurionpro.ems.dto.OperationResult;
import com.aurionpro.ems.model.Course;

public interface IStudentDao {
    OperationResult addStudent(Student student);
    List<Student> getAllStudents();
    boolean isStudentExists(String email, int rollNumber);
    boolean assignCourse(int studentId, int courseId);
    
     List<Course> viewCoursesByStudentId(int studentId);
}
