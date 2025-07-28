package com.aurionpro.ems.builder;

import java.math.BigDecimal;
import java.sql.Date;

import com.aurionpro.ems.Enum.Gender;
import com.aurionpro.ems.Enum.Role;
import com.aurionpro.ems.model.Admin;
import com.aurionpro.ems.model.Course;
import com.aurionpro.ems.model.Student;
import com.aurionpro.ems.model.Teacher;


public class UserBuilder {
	private int userId;
	private String firstName;
	private String lastName;
	private long mobileNumber;
	private String email;
	private Gender gender;
	private String city;
	private Role role;
	private Date createdAt;
	private boolean isFirstLogin;

	private int studentId;
	private int rollNumber;
	private BigDecimal averagePercentage;
	private int yearOfStudy;
	private int course_ID;

	private int teacherId;
	private int subjectId;
	private String qualification;
	private int experience;

	private int adminId;

	public UserBuilder setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public UserBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public UserBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	public UserBuilder setRole(Role role) {
		this.role = role;
		return this;
	}

	public UserBuilder setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public UserBuilder setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
		return this;
	}

	public UserBuilder setStudentId(int studentId) {
		this.studentId = studentId;
		return this;
	}

	public UserBuilder setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
		return this;
	}

	public UserBuilder setAveragePercentage(BigDecimal averagePercentage) {
		this.averagePercentage = averagePercentage;
		return this;
	}

	public UserBuilder setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
		return this;
	}

	public UserBuilder setCourse_ID(int course_ID) {
		this.course_ID = course_ID;
		return this;
	}

	public UserBuilder setTeacherId(int teacherId) {
		this.teacherId = teacherId;
		return this;
	}

	public UserBuilder setSubjectId(int subjectId) {
		this.subjectId = subjectId;
		return this;
	}

	public UserBuilder setQualification(String qualification) {
		this.qualification = qualification;
		return this;
	}

	public UserBuilder setExperience(int experience) {
		this.experience = experience;
		return this;
	}

	public UserBuilder setAdminId(int adminId) {
		this.adminId = adminId;
		return this;
	}


//	public Student getStudent() {
//		Student student = new Student(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin, rollNumber, averagePercentage, yearOfStudy);
//		student.setStudentId(studentId);
//		student.setCreatedAt(createdAt);
//		return student;
//	}

	public Teacher getTeacher() {
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		return teacher;
	}

	public Admin getAdmin() {
		Admin admin =  new Admin();
		admin.setAdminId(adminId);
		return admin;
	}
}
