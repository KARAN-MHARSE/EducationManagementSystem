package com.aurionpro.ems.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Subject;

public interface ICourseDao {
	public int addNewCourse(Course course);

	public List<Course> getAllCourses();
	
	public Course getCourseById(int courseId);

	public void printAllCourseDetails();

	public boolean addSubjectToCourse(String subjectName, int courseId);

	public boolean deleteCourse(int courseId);
	
	public List<Subject> getSubjectsInCourse(int courseId);

	public boolean reactivateACourse(int courseId);

	boolean viewAllDeleted();
}
