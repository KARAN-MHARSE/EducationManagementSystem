package com.aurionpro.ems.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.aurionpro.ems.dao.IStudentLoginDao;
import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.exceptions.AuthenticationException;

public class StudentLoginDao implements IStudentLoginDao {
	private Connection conn;

	public StudentLoginDao() {
		conn = Database.getConnection();
	}

	public boolean login(String username, String password) {

		String sql = "call ems.LoginForStudent(?,?,?,?)";
		try (CallableStatement statement = conn.prepareCall(sql)) {
			;
			statement.setString(1, username);
			statement.registerOutParameter(2, Types.INTEGER);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.registerOutParameter(4, Types.VARCHAR);

			statement.execute();

			Integer userId = statement.getInt(2);
			if (statement.wasNull()) {
				throw new AuthenticationException("Username not found");
			}

			String role = statement.getString(3);
			if (!"Student".equalsIgnoreCase(role)) {
				throw new AuthenticationException("You are not a student");
			}
			String storedPasswordHash = statement.getString(4);
			if (!password.equals(storedPasswordHash)) {
				throw new AuthenticationException("Wrong password");
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AuthenticationException("Database error during login");
		}

	}

	public boolean isFirstLogin(String userName) {
		String sql = "select u.is_first_login from ems.user u " + "join ems.authentication a on a.user_id = u.user_id "
				+ "where username= ?";

		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			preparedStatement.setString(1, userName);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getBoolean("is_first_login");
				} else {
					throw new RuntimeException("User not found: " + userName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error while checking first login", e);
		}
	}

	public boolean updatePasswordOnFirstLogin(String username, String newPassword) {
		String sql = "{CALL ems.updatePasswordOnFirstLogin(?, ?, ?)}";

		try (CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, newPassword);
			stmt.registerOutParameter(3, Types.VARCHAR);

			stmt.execute();
			String result = stmt.getString(3);

			if ("password updated".equalsIgnoreCase(result)) {
				return true;
			} else {
				throw new IllegalStateException(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
