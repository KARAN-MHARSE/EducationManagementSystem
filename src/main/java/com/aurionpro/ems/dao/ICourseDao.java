package com.aurionpro.ems.dao;

import java.util.List;

import com.aurionpro.ems.model.Course;

public interface ICourseDao {
	
	boolean addNewCourse(Course course);
	void showAllCourses();
	void printAllCourseDetails();
	boolean addSubjectToCourse(String subjectName, int courseId);
	void deleteCourse(int courseId);
	
}
