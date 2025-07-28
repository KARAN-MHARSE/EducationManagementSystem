package com.aurionpro.ems.model;

import java.sql.Timestamp;

import javax.management.relation.Role;

import com.aurionpro.ems.Enum.Gender;

public class Teacher extends User{

	private int teacherId;
    private int subjectId;
    private String qualification;
    private int experience;
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
	public Teacher(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Timestamp createdAt, boolean isFirstLogin, int subjectId,
			String qualification, int experience) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
		this.subjectId = subjectId;
		this.qualification = qualification;
		this.experience = experience;
	}
	public Teacher(String firstName, String lastName, long mobileNumber, String email, Gender gender,
			String city, Role role, Timestamp createdAt, boolean isFirstLogin) {
		super(firstName, lastName, mobileNumber, email, gender, city, role, createdAt, isFirstLogin);
	}
    
    

}
