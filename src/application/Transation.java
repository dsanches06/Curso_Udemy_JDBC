package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Transation {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();

			// não é para confirmar as operações
			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("update seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			//int x = 1;
			//if (x < 2) {
			//	throw new SQLException("Fake error");
			//}

			int rows2 = st.executeUpdate("update seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			// confirmar as operações
			conn.commit();

			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);

		} catch (SQLException e) {
			// para desfazer a operação, caso ocorra alguma erro
			try {
				conn.rollback();
				throw new DbException("Transation rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to roolback! Caused by: " + e.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
