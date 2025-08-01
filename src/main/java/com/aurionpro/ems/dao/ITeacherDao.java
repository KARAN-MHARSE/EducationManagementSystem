
package com.aurionpro.ems.dao;

import java.util.List;

import com.aurionpro.ems.models.Subject;
import com.aurionpro.ems.models.Teacher;
import com.aurionpro.ems.models.Teacher_Subject;

public interface ITeacherDao {

	boolean addNewTeacher(Teacher teacher);

	public List<Teacher> getAllTeachers();

	List<Teacher> getAllTeacherDetails();

	List<Subject> getAllSubjectDetails();

	boolean assignSubjectToTeacher(int teacherId, int subjectId);

	List<Subject> getSubjectsByTeacherId( int teacherId);

	Teacher printTeacherDetailsById(int teacherId);

	boolean deleteATeacher(int teacherId);

	public List<Teacher_Subject> getTeacherSubjectDetails(List<Teacher_Subject> teacher_subject_Details) ;

	boolean removeSubject(int teacherId, int subjectId);

	boolean checkTeacherId(int teacherId);

	boolean checkSubjectId(int subjectId);
}