package com.aurionpro.ems.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.aurionpro.ems.builder.UserBuilder;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.aurionpro.ems.exceptions.StudentNotFoundException;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Student;
import com.aurionpro.ems.utils.ResultSetConversion;

public interface IStudentDao {
//	Get
	public Student getStudentByEmail(String email);

	public Student getStudentByMobileNumber(Long mobileNumber);

	public Student getStudentByID(int id);

	Student getStudentByRollNumber(int rollNumber);

	public List<Student> getAllStudents();

	public boolean addNewStudent(Student student);

	public boolean isStudentExists(String email, int rollNumber);

	boolean assignCourse(int studentId, int courseId);
	
	public List<Course> viewCoursesByStudentId(int studentId);

	public boolean removCourseForStudent(int studentId,int courseID);

}

//