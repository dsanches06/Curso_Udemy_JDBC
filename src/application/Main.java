package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Main {

	public static void main(String[] args) throws SQLException {

		// para criar conexao
		Connection conn = null;
		// para criar o statement para executar squery
		Statement stat = null;
		// para obter o resultado da squery em forma de tabela
		ResultSet rs = null;

		try {
			// criar conexao
			conn = DB.getConnection();
			// criar um statement apartir da conexao
			stat = conn.createStatement();
			// resultset será igual ao statement execute squery
			rs = stat.executeQuery("select * from department");
			// usando metodo next() do resultset para percorrer o resultado
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			DB.closeStatement(stat);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
	}
}
