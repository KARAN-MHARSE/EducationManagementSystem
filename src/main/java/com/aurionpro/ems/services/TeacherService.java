package com.aurionpro.ems.services;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.ems.dao.implementations.TeacherDao;
import com.aurionpro.ems.dto.SubjectDetails;
import com.aurionpro.ems.dto.TeacherDetails;
import com.aurionpro.ems.dto.Teacher_Subject;
import com.aurionpro.ems.model.Teacher;

public class TeacherService {

	private TeacherDao teacherDao;
//	private List<Teacher> teachers = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	public TeacherService() {
		this.teacherDao = new TeacherDao();
	}

	public boolean addNewTeacher(Teacher teacher) {
		return teacherDao.addNewTeacher(teacher);
	}

	public List<TeacherDetails> ShowAllTeachers(List<TeacherDetails> teacherDetails) {
		return teacherDao.showAllTeachers(teacherDetails);
	}

	public boolean assignSubject(int teacherId, int subjectId) {
		return teacherDao.assignSubjectToTeacher(teacherId, subjectId);
	}

	
	public List<TeacherDetails> printAllTeacherDetails(List<TeacherDetails> teacherDetails) {
		return teacherDao.printAllTeacherDetails(teacherDetails);
	}
	
	public List<SubjectDetails> printAllSubjectDetails(List<SubjectDetails> subjectDetails) {
		return teacherDao.printAllSubjectDetails(subjectDetails);
	}
	
	public List<SubjectDetails> showSubjects(List<SubjectDetails> subjectDetails, int teacherId) {
		return teacherDao.getSubjectsByTeacherId(subjectDetails,teacherId);
	}

	public TeacherDetails searchATeacher(int teacherId, TeacherDetails detail) {
		return teacherDao.printTeacherDetailsById(teacherId,detail);
	}

	public boolean DeleteATeacher(int teacherId) {
		return teacherDao.deleteATeacher(teacherId);

	}
	
	public boolean RemoveASubject(int teacherId, int subjectId) {
		return teacherDao.removeSubject(teacherId, subjectId);
	}

	public List<Teacher_Subject> getTeacherSubjectDetails(List<Teacher_Subject> teacher_subject_Details) {
		return teacherDao.getTeacherSubjectDetails(teacher_subject_Details);
	}

	public boolean checkTeacherId(int teacherId) {
		return teacherDao.checkTeacherId(teacherId);
	}

	public boolean checkSubjectId(int subjectId) {
		return teacherDao.checkSubjectId(subjectId);
	}


}
