package com.aurionpro.ems;

import java.util.List;

import com.aurionpro.ems.controllers.FeeController;
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
		FeeController feeController = new FeeController();
		controller.displayAllStudents();
		controller.addStudent();
		
		//controller.assignCourseToStudent();
		//controller.viewCourseBYId();
		//feeController.viewFeeByStudentId();
		//feeController.viewCourseFeeById();
		//feeController.updateFeeById();
		//2
		//feeController.getTotalEarning();
		//feeController.getStudentsWithPendingFees();		
		//feeController.getStudentsWithTotalPaidFees();
		//feeController.start();
		
	}
}