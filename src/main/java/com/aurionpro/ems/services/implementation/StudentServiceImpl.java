package com.aurionpro.ems.services.implementation;

import java.util.List;

import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.model.Student;
import com.aurionpro.ems.services.IStudentService;

public class StudentServiceImpl implements IStudentService {

    private IStudentDao studentDao;

    public StudentServiceImpl() {
        this.studentDao = new StudentDaoImpl();
    }

    @Override
    public boolean addStudent(Student student) {
        // Check if student already exists
        String email = student.getEmail();
        int rollNumber = student.getRollNumber();

        if (studentDao.isStudentExists(email, rollNumber)) {
            System.out.println(" Student already exists with this email or roll number.");
            return false;
        }

        try {
    		return studentDao.addStudent(student);
    	} catch (Exception e) {
    		e.printStackTrace(); // Make sure this is there
    		return false;
    	}
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public boolean assignCourse(int studentId, int courseId) {
        return studentDao.assignCourse(studentId, courseId);
    }

    @Override
    public List<Course> viewCoursesByStudentId(int studentId) {
        return studentDao.viewCoursesByStudentId(studentId);
    }


}
