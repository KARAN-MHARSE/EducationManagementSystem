package com.aurionpro.ems.dao;

import com.aurionpro.ems.models.Course;

public interface ICourseDao {
	public boolean addNewCourse(Course course);

	public void showAllCourses();

	public void printAllCourseDetails();

	public boolean addSubjectToCourse(String subjectName, int courseId);

	public boolean deleteCourse(int courseId);
}
