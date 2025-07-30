package com.aurionpro.ems.dao;

import java.util.List;

import com.aurionpro.ems.dto.SubjectDetails;
import com.aurionpro.ems.dto.TeacherDetails;
import com.aurionpro.ems.dto.Teacher_Subject;
import com.aurionpro.ems.model.Teacher;

public interface ITeacherDao {

	boolean addNewTeacher(Teacher teacher);

	List<TeacherDetails> showAllTeachers(List<TeacherDetails> teacherDetails);

	List<TeacherDetails> printAllTeacherDetails(List<TeacherDetails> teacherDetails);

	List<SubjectDetails> printAllSubjectDetails(List<SubjectDetails> subjectDetails);

	boolean assignSubjectToTeacher(int teacherId, int subjectId);

	List<SubjectDetails> getSubjectsByTeacherId(List<SubjectDetails> subjectDetails, int teacherId);

	TeacherDetails printTeacherDetailsById(int teacherId, TeacherDetails detail);

	boolean deleteATeacher(int teacherId);

	List<Teacher_Subject> getTeacherSubjectDetails(List<Teacher_Subject> teacher_subject_Details);

	boolean removeSubject(int teacherId, int subjectId);

	boolean checkTeacherId(int teacherId);

	boolean checkSubjectId(int subjectId);
}
