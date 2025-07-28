package com.aurionpro.ems.dao;

import java.util.List;
import com.aurionpro.ems.model.Student;

public interface IStudentDao {
    boolean addStudent(Student student);
    List<Student> getAllStudents();
	boolean isStudentExists(String email, int rollNumber);
}
