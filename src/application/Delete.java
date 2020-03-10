package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Delete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			// prepared statement to execute squery
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

			// atribuir valor do id
			st.setInt(1, 2);

			int rows = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rows);

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
