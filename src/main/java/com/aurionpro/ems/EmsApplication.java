package com.aurionpro.ems;

import java.util.List;

import com.aurionpro.ems.controllers.DashboardController;
import com.aurionpro.ems.controllers.FeeController;
import com.aurionpro.ems.controllers.StudentController;
import com.aurionpro.ems.controllers.StudentLoginController;
import com.aurionpro.ems.dao.IStudentDao;
import com.aurionpro.ems.dao.implementation.StudentDaoImpl;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.dto.Dashboard;
import com.aurionpro.ems.model.Student;

public class EmsApplication{
	public static void main(String args[]) {
		System.out.println("Welcome");
		Database.getConnection();
		StudentController controller = new StudentController();
		FeeController feeController = new FeeController();
		StudentLoginController loginController = new StudentLoginController();
		DashboardController dashboardController = new DashboardController();
		
		loginController.startLogin();
		//controller.displayAllStudents();
		//controller.addStudent();
		
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
		
		
		List<Dashboard> dashboards = dashboardController.getDashboardData();

        if (dashboards.isEmpty()) {
            System.out.println("No data available in the dashboard.");
            return;
        }


        System.out.printf("%-5s %-10s %-20s %-30s %-30s %-30s %-10s %-10s %-10s%n",
                "SrNo", "StudID", "Student Name", "Courses", "Subjects", "Teachers",
                "Total", "Paid", "Pending");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Dashboard d : dashboards) {
            
            String[] courses = splitLines(d.getCourses());
            String[] subjects = splitLines(d.getSubjects());
            String[] teachers = splitLines(d.getTeachers());

           
            courses = addNumbering(courses);
            subjects = addNumbering(subjects);
            teachers = addNumbering(teachers);

           
            int maxLines = Math.max(courses.length, Math.max(subjects.length, teachers.length));
            maxLines = Math.max(maxLines, 1); // at least one line

           
            for (int i = 0; i < maxLines; i++) {
                if (i == 0) {
                    
                    System.out.printf("%-5d %-10d %-20s %-30s %-30s %-30s %10.2f %10.2f %10.2f%n",
                            d.getSrNo(),
                            d.getStudentId(),
                            d.getStudentName(),
                            safeGet(courses, i),
                            safeGet(subjects, i),
                            safeGet(teachers, i),
                            d.getTotalFee(),
                            d.getTotalPaid(),
                            d.getPendingFee()
                    );
                } else {
                    
                    System.out.printf("%-5s %-10s %-20s %-30s %-30s %-30s %10s %10s %10s%n",
                            "",
                            "",
                            "",
                            safeGet(courses, i),
                            safeGet(subjects, i),
                            safeGet(teachers, i),
                            "",
                            "",
                            ""
                    );
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    // Helper: split string by newline, or return array with empty string if null/empty
    private static String[] splitLines(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new String[]{""};
        }
        return str.split("\\n");
    }

    // Helper: safely get array element or empty string if out of bounds
    private static String safeGet(String[] arr, int index) {
        if (index < arr.length) {
            return arr[index];
        }
        return "";
    }

    // Helper: add numbering "1. item", "2. item", etc. to array elements
    private static String[] addNumbering(String[] arr) {
        String[] numbered = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String trimmed = arr[i].trim();
            numbered[i] = (trimmed.isEmpty() ? "" : (i + 1) + ". " + trimmed);
        }
        return numbered;
    }
	}
