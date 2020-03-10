package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Update {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
           //prepared statement to execute squery
			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE DepartmentId = ?");

			// atribuir valor
			st.setDouble(1, 200.00);
			st.setInt(2, 2);

			int rows = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rows);

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}