package com.aurionpro.ems.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import com.aurionpro.ems.database.Database;
import com.aurionpro.ems.exceptions.StudentNotFoundException;


public class UserUtil {
	private static final Connection connection = Database.getConnection();

	public static boolean deleteUserById(int id) throws SQLException {
		if (!isActiveUserPresent(id)) return false;

		String sql = "UPDATE ems.user SET isActive = false WHERE user_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			int rowsAffectedCount = statement.executeUpdate();
			return rowsAffectedCount > 0;
		}
	}

	public static boolean isActiveUserPresent(int id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM ems.user WHERE user_id = ? AND isActive = true";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0;
				}
			}
		}
		return false;
	}
}
