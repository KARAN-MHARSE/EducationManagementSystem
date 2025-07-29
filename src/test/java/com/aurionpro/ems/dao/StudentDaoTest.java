package com.aurionpro.ems.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.xml.crypto.Data;

import org.junit.jupiter.api.Test;

import com.aurionpro.ems.database.Database;

class StudentDaoTest {
	Connection connection = null;
	
	public StudentDaoTest() {
		connection = Database.getConnection();
	}

	@Test
	void testStudentDao() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStudentByEmail() {
		assert
	}

	@Test
	void testGetAllStudents() {
		fail("Not yet implemented");
	}

}
