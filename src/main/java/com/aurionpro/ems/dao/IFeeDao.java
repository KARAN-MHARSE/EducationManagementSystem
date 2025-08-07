package com.aurionpro.ems.dao;

import java.util.List;

import com.aurionpro.ems.dto.PendingFeeStudentDto;
import com.aurionpro.ems.dto.StudentFeeSummary;
import com.aurionpro.ems.models.Course;
import com.aurionpro.ems.models.Fee;



public interface IFeeDao {

	List<StudentFeeSummary> getFeesByStudentId(int studentId);
	List<Course> getFeesByCourseId(int courseId);
	boolean updateFeeByCourseId(int courseId,double newFee);
	double getTotalEarnings();
	List<PendingFeeStudentDto> getStudentsWithPendingFees();
	List<PendingFeeStudentDto> getStudentsWithTotalPaidFees();
	List<Course> getFeesByCourse();
	
	
}