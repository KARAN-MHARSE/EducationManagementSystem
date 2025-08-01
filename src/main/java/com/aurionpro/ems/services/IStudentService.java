package com.aurionpro.ems.services;

import java.util.List;

import com.aurionpro.ems.dto.OperationResult;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.model.Student;

public interface IStudentService {
	 OperationResult  addStudent(Student student);

	List<Student> getAllStudents();

	public boolean assignCourse(int studentId, int courseId);

	List<Course> viewCoursesByStudentId(int studentId);

}
