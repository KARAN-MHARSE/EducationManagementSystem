package com.aurionpro.ems.services.implementation;

import java.util.List;

import com.aurionpro.ems.dao.IFeeDao;
import com.aurionpro.ems.dao.implementation.FeeDaoImpl;
import com.aurionpro.ems.dto.PendingFeeStudentDto;
import com.aurionpro.ems.dto.StudentFeeSummary;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.model.Fee;
import com.aurionpro.ems.services.IFeeService;

public class FeeServiceImpl implements IFeeService {
	
	IFeeDao feeDao;
	
	public FeeServiceImpl() {
		feeDao = new FeeDaoImpl();
	}

	@Override
	public List<StudentFeeSummary> getFeesByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return feeDao.getFeesByStudentId(studentId);
	}

	@Override
	public List<Course> getFeesByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return feeDao.getFeesByCourseId(courseId);
	}

	@Override
	public boolean updateFeeByCourseId(int courseId, double newFee) {
		// TODO Auto-generated method stub
		return feeDao.updateFeeByCourseId(courseId, newFee);
	}

	@Override
	public double getTotalEarnings() {
		// TODO Auto-generated method stub
		return feeDao.getTotalEarnings();
	}

	@Override
	public List<PendingFeeStudentDto> getStudentsWithPendingFees() {
		// TODO Auto-generated method stub
		return feeDao.getStudentsWithPendingFees();
	}

	@Override
	public List<PendingFeeStudentDto> getStudentsWithTotalPaidFees() {
		// TODO Auto-generated method stub
		return feeDao.getStudentsWithTotalPaidFees();
	}
	

}
