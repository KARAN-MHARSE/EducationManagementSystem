package com.aurionpro.ems;

import java.util.List;

import com.aurionpro.ems.controllers.StudentController;
import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.model.Student;

public class EmsApplication{
	public static void main(String args[]) {
		System.out.println("Welcome");
		Database.getConnection();
		StudentController controller = new StudentController();
		//controller.addStudent();
		//controller.displayAllStudents();
		controller.assignCourseToStudent();
		controller.viewCourseBYId();
		
		
	}
}