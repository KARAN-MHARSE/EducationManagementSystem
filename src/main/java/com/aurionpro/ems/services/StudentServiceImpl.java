package com.aurionpro.ems.services;

import java.util.List;

import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.model.Student;

public class StudentServiceImpl implements IStudentService {

    private IStudentDao studentDao;

    public StudentServiceImpl() {
        this.studentDao = new StudentDaoImpl();
    }

    @Override
    public boolean addStudent(Student student) {
        // Step 1: Check if student already exists
        String email = student.getUser().getEmail();
        int rollNumber = student.getRollNumber();

        if (studentDao.isStudentExists(email, rollNumber)) {
            System.out.println("❌ Student already exists with this email or roll number.");
            return false;
        }

        // Step 2: Add the student if not already present
        return studentDao.addStudent(student);
    }

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDao.getAllStudents();
	}

}
