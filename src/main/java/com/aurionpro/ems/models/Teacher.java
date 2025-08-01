package com.aurionpro.ems.models;

import java.sql.Date;

import com.aurionpro.ems.enums.Gender;
import com.aurionpro.ems.enums.Role;
import com.google.protobuf.Timestamp;

public class Teacher extends User {

	private int teacherId;
	private int subjectId;
	private String qualification;
	private int experience;

	

	public Teacher(int userId, String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Date createdAt, boolean isFirstLogin, boolean isActive, int teacherId,
			int subjectId, String qualification, int experience) {
		super(userId, firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin, isActive);
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.qualification = qualification;
		this.experience = experience;
	}

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public Teacher(String firstName, String lastName, long mobileNumber, String email, Gender gender, String city,
			Role role, java.util.Date createdAt, boolean b, String qualification2, int experience2) {
		// TODO Auto-generated constructor stub
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}