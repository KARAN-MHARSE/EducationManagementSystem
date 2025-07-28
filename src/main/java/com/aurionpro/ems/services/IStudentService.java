package com.aurionpro.ems.services;

import java.util.List;

import com.aurionpro.ems.model.Student;



public interface IStudentService {
    boolean addStudent(Student student);
    List<Student> getAllStudents();

}




