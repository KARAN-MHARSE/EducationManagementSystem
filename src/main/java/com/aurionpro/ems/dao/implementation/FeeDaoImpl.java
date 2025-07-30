package com.aurionpro.ems.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.ems.dao.IFeeDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.dto.PendingFeeStudentDto;
import com.aurionpro.ems.dto.StudentFeeSummary;
import com.aurionpro.ems.model.Course;


public class FeeDaoImpl implements IFeeDao {

	private Connection conn;

	public FeeDaoImpl() {
		conn = Database.getConnection();
	}

	@Override
	public List<StudentFeeSummary> getFeesByStudentId(int studentId) {
		String sql = " call ems.View_Fees_By_StudentId(?);";

		List<StudentFeeSummary> feeSummaryList = new ArrayList<>();

		try (CallableStatement callableStatement = conn.prepareCall(sql)) {

			callableStatement.setInt(1, studentId);

			ResultSet resultSet = callableStatement.executeQuery();

			while (resultSet.next()) {
				StudentFeeSummary summary = new StudentFeeSummary();
				summary.setStudentId(resultSet.getInt("student_id"));
				summary.setFullName(resultSet.getString("full_name"));
				summary.setTotalFee(resultSet.getDouble("total_fee"));
				summary.setTotalPaidFee(resultSet.getDouble("total_paid"));
				summary.setPendingFee(resultSet.getDouble("pending_fee"));
				feeSummaryList.add(summary);
			}

		} catch (SQLException exception) {
			System.out.println("Error while geting student" + exception.getMessage());
		}

		return feeSummaryList;
	}

	@Override
	public List<Course> getFeesByCourseId(int courseId) {
		String sql = "select c.name,c.course_fee  from ems.course c where course_id = ? and is_deleted =0";
		List<Course> fees = new ArrayList<Course>();

		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.setInt(1, courseId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Course course = new Course();
				course.setName(resultSet.getString(1));
				course.setCourseFee(resultSet.getDouble(2));

				fees.add(course);
			}

		} catch (SQLException exception) {
			System.out.println("error occured while fethching " + exception.getMessage());
		}

		return fees;
	}

	public boolean updateFeeByCourseId(int courseId, double newFee) {
		String sql = "update ems.course set course_fee = ? where course_id = ? and is_deleted = 0;";

		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			preparedStatement.setDouble(1, newFee);
			preparedStatement.setInt(2, courseId);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Course fee updated successfully for course ID: " + courseId);
				return true;
			} else {
				System.out.println("No active course found with ID: " + courseId);
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Error while updating course fee: " + e.getMessage());
			return false;
		}
	}

	@Override
	public double getTotalEarnings() {
		String sql = "select sum(paid_fees) as total_earning from ems.fees where is_deleted = 0";
		double totalEarning = 0.0;

		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				totalEarning = rs.getDouble("total_earning");
				if (rs.wasNull()) {
					totalEarning = 0.0;
				}
			}

		} catch (SQLException e) {
			System.out.println(" Error fetching total earnings: " + e.getMessage());
		}

		return totalEarning;
	}
	
	
	 @Override
	    public List<PendingFeeStudentDto> getStudentsWithPendingFees() {
	        List<PendingFeeStudentDto> pendingList = new ArrayList<>();

	        String procedure = "{call ems.view_student_with_pending_fees()}";

	        try (
	         
	            CallableStatement stmt = conn.prepareCall(procedure);
	            ResultSet rs = stmt.executeQuery();
	        ) {
	            while (rs.next()) {
	                int studentId = rs.getInt("student_id");
	                String fullName = rs.getString("full_name");
	                String email = rs.getString("email");
	                String mobileNumber = rs.getString("mobile_number");
	                double pendingFee = rs.getDouble("pending_fee");

	                PendingFeeStudentDto dto = new PendingFeeStudentDto(
	                        studentId, fullName, email, mobileNumber,
	                        pendingFee
	                );

	                pendingList.add(dto);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return pendingList;
	    }
	 @Override
	    public List<PendingFeeStudentDto> getStudentsWithTotalPaidFees() {
	        List<PendingFeeStudentDto> pendingList = new ArrayList<>();

	        String procedure = "{call ems.view_student_with_total_fees_paid()}";

	        try (
	         
	            CallableStatement stmt = conn.prepareCall(procedure);
	            ResultSet rs = stmt.executeQuery();
	        ) {
	            while (rs.next()) {
	                int studentId = rs.getInt("student_id");
	                String fullName = rs.getString("full_name");
	                String email = rs.getString("email");
	                String mobileNumber = rs.getString("mobile_number");
	                double pendingFee = rs.getDouble("pending_fee");

	                PendingFeeStudentDto dto = new PendingFeeStudentDto(
	                        studentId, fullName, email, mobileNumber,
	                        pendingFee
	                );

	                pendingList.add(dto);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return pendingList;
	    }

}
